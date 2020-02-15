package com.hstc.yak.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hstc.yak.service.AdminService;

/**
 * Created by hp on 2019/10/9.
 */
@Service(version = "1.0.0",interfaceClass = AdminService.class)
public class AdminServiceImpl implements AdminService {
    @Override
    public String Login(String username, String password) {
        return null;
    }
}
