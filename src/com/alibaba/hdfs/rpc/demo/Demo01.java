package com.alibaba.hdfs.rpc.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.junit.Before;
import org.junit.Test;


import java.net.URI;

/**
 * Created by Dali on 2018/1/4.
 */
public class Demo01 {
    private FileSystem fs = null;
    private Configuration conf = null;
   @Before//创建文件管理系统的客户端实例对象,然后调用其方法
    public void init() throws Exception{
        conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.100.129:9000");
        fs = FileSystem.get(new URI("hdfs://192.168.100.129:9000"),conf,"hadoop");
//        fs = FileSystem.get(new URI("hdfs://192.168.100.129:9000"),conf);
   }
    /**
     * Copy file from local to hdfs
     * @throws Exception
     */
   @Test
    public void testUpload() throws Exception{
      fs.copyFromLocalFile(new Path("C:\\opt\\lida_2.txt"),new Path("/hive/data/lida_2.txt"));
   }
    /**
     *  Copy file from hdfs to local
     * @throws Exception
     */
    @Test
    public void testDownload () throws Exception{
       fs.copyToLocalFile(new Path("/test_1.txt"),new Path("C:/opt/"));
   }
    /**
     * Make directorys
     */
    @Test
    public void testMkdirs() throws Exception{
        boolean success = fs.mkdirs(new Path("/hive/data/lida/lida"));
        System.out.println(success);
    }
    /**
     *  Delete directorys   recursion
     */
    @Test
    public void testDelete() throws Exception{
        boolean success = fs.delete(new Path("/hive/data"),true);
        System.out.println(success);
    }
    /**
     * ls
     */
    @Test
    public void listTest() throws Exception{
        FileStatus[] fileStatuses = fs.listStatus(new Path("/hive"));
        for(FileStatus fileStatus : fileStatuses){
            String name = fileStatus.getPath().getName();
            System.out.println("name : " + name );
            long accessTime = fileStatus.getAccessTime();
            System.out.println("accessTIme: " + accessTime);
            long blockSize = fileStatus.getBlockSize();
            System.out.println("blockSize: "+ blockSize);
            long len = fileStatus.getLen();
            System.out.println("len: " +len);
            String owner = fileStatus.getOwner();
            System.out.println("owner: "+owner);
            FsPermission permission = fileStatus.getPermission();
            System.out.println("permission: "+permission);
            System.out.println("------------------------------ 分割线 ------------------------");
        }
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/hive"), true);
        while (files.hasNext()) {
            LocatedFileStatus localStatus = files.next();
            String owner = localStatus.getOwner();
            System.out.println("owner: "+owner);
        }

    }
    @Test
    public void testRandomAccess(){

    }
}
