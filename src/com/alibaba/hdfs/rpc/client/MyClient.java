package com.alibaba.hdfs.rpc.client;

import com.alibaba.hdfs.rpc.service.ClientNameNodeProtocol;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Dali on 2018/1/5.
         */
public class MyClient {
    public static void main(String[] args) throws IOException {
        ClientNameNodeProtocol namenode = RPC.getProxy(ClientNameNodeProtocol.class, 100L, new InetSocketAddress("127.0.0.1", 8888), new Configuration());
        String metaData = namenode.getMetaData("/hive/data");
        System.out.println("metaData is : " + metaData);
    }
}
