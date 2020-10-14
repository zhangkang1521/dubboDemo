package org.zk.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserProvider {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-user.xml");
		ctx.start();
		System.out.println("服务提供启动...");
		System.in.read(); // waiting
	}
}
