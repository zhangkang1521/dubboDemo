package org.zk.dubbo.consumer;

import java.lang.reflect.Proxy;

public class RpcProxy {

	public static <T> T create(Class<?> clazz) {
		MethodProxy proxy = new MethodProxy(clazz);
		return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, proxy);
	}
}
