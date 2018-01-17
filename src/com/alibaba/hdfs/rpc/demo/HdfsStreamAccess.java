package com.alibaba.hdfs.rpc.demo;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;


/**
 *  通过流操作
 * Created by Dali on 2018/1/8.
 */
public class HdfsStreamAccess {
    private FileSystem fs = null;
    private Configuration conf = null;

    @Before
    public void init() throws Exception{
        conf = new Configuration();
        fs = FileSystem.get(new URI("hdfs://192.168.100.129:9000"), conf, "hadoop");
    }
    @Test
      public void testUpload() throws IOException {
        FSDataOutputStream out = fs.create(new Path("/user/data/952575.lida"));//输出流, 用于上传至hdfs
        FileInputStream in = new FileInputStream("C:\\opt\\upload\\952575.lida");//输入流, 用于读取需要上传的文件
        int result = IOUtils.copy(in, out);//IO工具
        System.out.println("the result is : " + result);
    }

    @Test
    public void testDownload() throws Exception{
            FSDataInputStream in = fs.open(new Path("/user/data/952575.lida"));
            in.seek(35);
            FileOutputStream out =  new FileOutputStream("C:\\opt\\upload\\952575_22.lida");
            int result = IOUtils.copy(in, out);
            System.out.println(" The result is : " + result);
    }
    @Test
    public  void testCat() throws Exception{
        FSDataInputStream in = fs.open(new Path("/user/data/952575.lida"));
        int copy = IOUtils.copy(in, System.out);
//        System.out.println("The result is copy " + copy);

    }
}

