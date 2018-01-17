package com.alibaba.hdfs.rpc.publish;

import com.alibaba.hdfs.rpc.service.ClientNameNodeProtocol;
import com.alibaba.hdfs.rpc.service.UserService;
import com.alibaba.hdfs.rpc.service.impl.ClientNameNodeSystem;
import com.alibaba.hdfs.rpc.service.impl.UserServiceImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC.Server;
import org.apache.hadoop.ipc.RPC.Builder;

import java.io.IOException;
/**
 * Custom util to publish service
 * Created by Dali on 2018/1/5.
 */
public class PublishUtils {
    public static void main(String[] args) throws IOException {
        //server1
        Builder builder1 = new Builder(new Configuration());
        builder1.setBindAddress("127.0.0.1")
                .setPort(8888)
                .setProtocol(ClientNameNodeProtocol.class)
                .setInstance(new ClientNameNodeSystem());
        Server server1 = builder1.build();
        server1.start();
        //server2
        Builder builder2 = new Builder(new Configuration());
        builder2.setBindAddress("localhost")
                .setPort(9999)
                .setProtocol(UserService.class)
                .setInstance(new UserServiceImpl());
        Server server2 = builder2.build();
        server2.start();
    }
}
