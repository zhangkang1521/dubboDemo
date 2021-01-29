package org.zk.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zk.dubbo.interfaces.DemoService;

/**
 * 消费者，调用服务好像调用本地一样
 */
public class Consumer {
    // vm参数：-javaagent:E:/tmp/dubbo-b/anchor-agent-0.0.1-SNAPSHOT.jar -Danchor.test=test
    public static void main( String[] args ) throws Exception{
        // 1个服务：ls /dubbo/CommonConfiguration/ROUTE_CYCLE_10.43.2.86=10.43.2.86@dubbo-b
        // 2个服务：ls /dubbo/CommonConfiguration/ROUTE_CYCLE_10.43.2.86=10.43.2.86@dubbo-b,10.43.2.75@dubbo-b

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext.xml" });
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService");
//        System.out.println(JSON.toJSONString(demoService));
        for (int i=0; i< 1; i++) {
            System.out.println(demoService.sayHello("zhangkang"));
        }
        System.in.read();
//        System.out.println(hello);
    }
}
