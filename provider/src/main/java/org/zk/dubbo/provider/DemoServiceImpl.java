package org.zk.dubbo.provider;

import com.alibaba.dubbo.rpc.RpcContext;
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
        RpcContext rpcContext  = RpcContext.getContext();
        System.out.println(rpcContext.getAttachment("application"));
        logger.info("=============== sayHello invoked ===========");
        return "hello, local," + name;
    }

}
