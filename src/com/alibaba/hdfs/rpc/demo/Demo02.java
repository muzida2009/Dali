package com.alibaba.hdfs.rpc.demo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

/**
 * Created by Dali on 2018/1/5.
 */
public class Demo02 {
    private FileSystem  fs = null;
    private Configuration conf = null;
    @Before
    public void init() throws Exception{
        conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.100.129:9000");
        fs = FileSystem.get(new URI("hdfs://192.168.100.129:9000"),conf,"hadoop");

    }
    /**
     *  Rename directory
     * @throws Exception
     */
    @Test
    public void testRename() throws Exception{
        boolean rename = fs.rename(new Path("/hive/bigdata"), new Path("/hive/data"));
        System.out.println("rename: " + rename );
    }
    /**
     *  Concentrate on the difference between while and for loop
     */
    @Test
    public void testList() throws IOException {
        RemoteIterator<LocatedFileStatus> fileStatuses = fs.listFiles(new Path("/hive/data/"),true);
        while(fileStatuses.hasNext()){
            LocatedFileStatus fileStatus = fileStatuses.next();
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
            String[] hosts = blockLocation.getHosts();
                String[] names = blockLocation.getNames();
                System.out.println("names is : " +Arrays.toString(names));
                long offset = blockLocation.getOffset();
                System.out.println("offset is : " + offset);
                String[] topologyPaths = blockLocation.getTopologyPaths();
                System.out.println("topologyPaths is : " + Arrays.toString(topologyPaths));
                System.out.println("hosts is : " + Arrays.toString(hosts));
                System.out.println("-------------------------------分隔线-----------------------------");
            }
        }
    }
}
