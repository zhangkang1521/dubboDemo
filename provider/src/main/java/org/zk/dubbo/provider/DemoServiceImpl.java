package org.zk.dubbo.provider;

import org.zk.dubbo.interfaces.DemoService;

/**
 * 服务提供
 * Created by zhangkang on 2016/12/30.
 */
public class DemoServiceImpl implements DemoService {


    public String sayHello(String name) {
        return "hello, local," + name;
    }

}
