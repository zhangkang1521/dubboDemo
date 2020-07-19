package org.zk.dubbo.consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.zk.dubbo.interfaces.InvokerProtocol;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.rmi.registry.RegistryHandler;

public class MethodProxy implements InvocationHandler {

	private Class<?> clazz;

	public MethodProxy(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (Object.class.equals(method.getDeclaringClass())) {
			return method.invoke(this, args);
		} else {
			return rpcInvoke(method, args);
		}
	}

	/**
	 * 远程调用
	 * @param method
	 * @param args
	 * @return
	 */
	private Object rpcInvoke(Method method, Object[] args) {
		InvokerProtocol invokerProtocol = new InvokerProtocol();
		invokerProtocol.setClassName(this.clazz.getName());
		invokerProtocol.setMethodName(method.getName());
		invokerProtocol.setParamTypes(method.getParameterTypes());
		invokerProtocol.setParamValues(args);

		final RpcProxyHandler consumerHandler = new RpcProxyHandler();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							ChannelPipeline pipeline = socketChannel.pipeline();
							pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
							pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
							pipeline.addLast("encoder", new ObjectEncoder());
							pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
							pipeline.addLast("handler", consumerHandler);
						}
					});

			ChannelFuture future = b.connect("localhost", 8080).sync();
			future.channel().writeAndFlush(invokerProtocol).sync();
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}

		return consumerHandler.getResponse();
	}
}
