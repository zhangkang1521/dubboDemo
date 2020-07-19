package org.zk.dubbo.consumer;

import org.zk.dubbo.interfaces.DemoService;

public class RpcConsumer {

	public static void main(String[] args) {
		DemoService demoService = RpcProxy.create(DemoService.class);
		System.out.println(demoService.toString());
		System.out.println(demoService.sayHello("test"));
	}
}
