package org.zk.dubbo.provider;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zk.dubbo.interfaces.DemoService;
import org.zk.dubbo.interfaces.UserService;

/**
 * 服务提供
 * Created by zhangkang on 2016/12/30.
 */
public class DemoServiceImpl implements DemoService {

    private static Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    private UserService userService;

    public String sayHello(String name) {
        logger.info("=============== sayHello invoked ===========");
        RpcContext context = RpcContext.getContext();
        String consumerApplicationName = (String) context.get(Constants.APPLICATION_KEY);
        System.out.println(consumerApplicationName);
        userService.save();
        return "hello, local," + name;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
