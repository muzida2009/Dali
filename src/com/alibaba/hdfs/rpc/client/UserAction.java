package com.alibaba.hdfs.rpc.client;

import com.alibaba.hdfs.rpc.service.UserService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;

/**
 * Created by Dali on 2018/1/5.
 */
public class UserAction {
    @Test
    public void login() throws IOException {
        String username = "jerry";
        String passwd = "1234";
        //Get proxy
        UserService proxy = RPC.getProxy(UserService.class, 100L, new InetSocketAddress("localhost", 9999), new Configuration());
        String result = proxy.login(username, passwd);
        System.out.println(" The result of login : " + result);
    }
}
