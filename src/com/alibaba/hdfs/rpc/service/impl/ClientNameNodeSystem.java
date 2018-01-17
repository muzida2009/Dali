package com.alibaba.hdfs.rpc.service.impl;

import com.alibaba.hdfs.rpc.service.ClientNameNodeProtocol;

/**
 * Created by Dali on 2018/1/5.
 */
public class ClientNameNodeSystem implements ClientNameNodeProtocol{
    @Override
    public String getMetaData(String path) {
        return path + ", replication: 3 , block1,block2,block3....";
    }
}
