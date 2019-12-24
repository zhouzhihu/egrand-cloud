package com.egrand.cloud.oauth.server.service.feign;

import com.egrand.cloud.core.model.ResultBody;
import com.egrand.cloud.ram.client.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClientImpl implements UserServiceClient {

    @Override
    public ResultBody<UserAccount> userLogin(String username) {
        System.out.println("调用失败！");
        return ResultBody.failed();
    }

    @Override
    public ResultBody<String> feign(String username) {
        System.out.println("调用失败！");
        return ResultBody.failed();
    }
}
