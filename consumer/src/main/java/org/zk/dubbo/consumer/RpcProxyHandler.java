package org.zk.dubbo.consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class RpcProxyHandler extends ChannelInboundHandlerAdapter {

	private Object response;

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("收到返回值：" + msg);
		this.response = msg;
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.err.println("客户端异常：");
		cause.printStackTrace();
	}

	public Object getResponse() {
		return response;
	}
}
