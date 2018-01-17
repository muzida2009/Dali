package com.alibaba.hdfs.rpc.service;

/**
 * Created by Dali on 2018/1/5.
 */
public interface UserService {
    public static final long versionID = 100L;
    public String login(String username, String passwd);
}
