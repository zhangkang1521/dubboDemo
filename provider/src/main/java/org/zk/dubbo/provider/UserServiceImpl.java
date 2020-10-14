package org.zk.dubbo.provider;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.rpc.RpcContext;
import org.zk.dubbo.interfaces.UserService;

public class UserServiceImpl implements UserService {
	public void save() {
		RpcContext context = RpcContext.getContext();
		String consumerApplicationName = (String) context.get(Constants.APPLICATION_KEY);
		System.out.println(consumerApplicationName);
		System.out.println("save");
	}
}
