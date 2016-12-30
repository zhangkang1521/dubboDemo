package org.zk.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zk.dubbo.interfaces.DemoService;

/**
 * 消费者，调用服务好像调用本地一样
 */
public class Consumer {
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext.xml" });
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService");
        String hello = demoService.sayHello("tom");
        System.out.println(hello);
    }
}
