package com.alibaba.mapred.wordcount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;

/**
 * Created by Dali on 2018/1/16.
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
       /* if(args == null || args.length == 0){
            args = new String[2];
            args[0] = "hdfs://192.168.100.129:9000/wordcount/input/wordcount.txt";
            args[1] = "hdfs://192.168.100.129:9000/wordcount/output";
        }*/
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(WordCountDriver.class);//指定本程序的jar所在的本地路径

        //1. 指定当前job所要使用的mapper和reducer类
        job.setMapperClass(WordCountMapper.class);//mapper
        job.setReducerClass(WordCountReducer.class); //reducer

        //2. 指定mapper输出的数据的KV类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //3. 指定最终输出的数据的KV类型(此处对应的是reducer的输出KV类型)
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

    //4. 指定当前job输入的原始文件的目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
    //5. 指定当前job输出结果所在的目录
        FileOutputFormat.setOutputPath(job,new Path( args[1]));

//        job.submit();//没有提示信息, 不知job是否运行完成
        boolean success = job.waitForCompletion(true);
        System.exit(success ? 0 : 1);
    }
}
