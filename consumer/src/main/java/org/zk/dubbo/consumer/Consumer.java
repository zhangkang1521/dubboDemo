package org.zk.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zk.dubbo.interfaces.DemoService;

/**
 * 消费者，调用服务好像调用本地一样
 */
public class Consumer {
    public static void main( String[] args ) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext.xml" });
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService");
//        System.out.println(JSON.toJSONString(demoService));
//        for (int i=0; i< 5; i++) {
            System.out.println(demoService.sayHello("zhangkang"));
//        }
//        System.out.println(hello);
    }
}
