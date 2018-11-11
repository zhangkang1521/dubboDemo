package org.zk.dubbo.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zk.dubbo.interfaces.DemoService;

/**
 * 服务提供
 * Created by zhangkang on 2016/12/30.
 */
public class DemoServiceImpl implements DemoService {

    private static Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    public String sayHello(String name) {
        logger.info("=============== sayHello invoked ===========");
        return "hello, " + name;
    }

    public String sayWorld(String name) {
        logger.info("sayWorld invoked");
        return "world, " + name;
    }
}
