package org.zk.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务提供
 * Created by zhangkang on 2016/12/30.
 */
public class Provider {
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ctx.start();
        System.out.println("服务提供启动...");
        System.in.read(); // waiting
    }
}
