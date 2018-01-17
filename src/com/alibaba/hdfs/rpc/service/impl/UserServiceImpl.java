package com.alibaba.hdfs.rpc.service.impl;

import com.alibaba.hdfs.rpc.service.UserService;

import java.util.Date;

/**
 * Created by Dali on 2018/1/5.
 */
public class UserServiceImpl implements UserService {
    @Override
    public String login(String username, String passwd) {
        return " The current user : " + username + " logined success ! " + " ," + new Date().toString();
    }
}
