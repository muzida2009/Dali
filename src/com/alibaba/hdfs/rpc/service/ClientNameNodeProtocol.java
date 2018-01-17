package com.alibaba.hdfs.rpc.service;
/**
 * Custom Client-NameNode-Protocol rpc
 * Created by Dali on 2018/1/5.
 */
public interface ClientNameNodeProtocol {
    public static final long versionID = 100L;
    public String getMetaData(String Path);
}
