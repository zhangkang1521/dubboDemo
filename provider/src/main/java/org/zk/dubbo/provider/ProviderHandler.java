package org.zk.dubbo.provider;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.zk.dubbo.interfaces.InvokerProtocol;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProviderHandler extends ChannelInboundHandlerAdapter {

	private Map<String, Object> exporter = new ConcurrentHashMap<>();

	public ProviderHandler() {
		// 服务暴露
		exporter.put("org.zk.dubbo.interfaces.DemoService", new DemoServiceImpl());
	}

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		 InvokerProtocol request = (InvokerProtocol)msg;
		System.out.println("收到客户端调用，class:" + request.getClassName() + ",method:" + request.getMethodName());
		Object ref = exporter.get(request.getClassName());
		Method method = ref.getClass().getMethod(request.getMethodName(), request.getParamTypes());
		Object result = method.invoke(ref, request.getParamValues());
		ctx.write(result);
		ctx.flush();
		ctx.close();
	}
}
