package com.alibaba.mapred.flowcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by Dali on 2018/1/17.
 */
public class FlowCountDriver {
    public static void main(String[] args) throws Exception {
        /*
            1. 获取job的实例对象:参数conf
            2. 根据类的class设置jar所在路径
            3. 指定当前job需要使用的mapper和reducer业务类
            4. 指定mapper输出数据的KV类型
            5. 指定reducer输出数据的KV类型
            6. 指定job输入的原始文件的所在目录
            7. 指定job输出结果的所在目录
            8. 将job提交给yarn
         */

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(FlowCountDriver.class);

        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean success = job.waitForCompletion(true);
        System.exit(success ? 0 : 1);

    }
    /**
     *  Text phone number
     *  FlowBean Encapsolate upload and download
     */
    static class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] datas = line.split("\t");
            String phoneNum = datas[1];
            long upFlow = Long.parseLong(datas[datas.length - 3]);
            long downFlow = Long.parseLong(datas[datas.length - 2]);
            context.write(new Text(phoneNum), new FlowBean(upFlow, downFlow));
        }
    }
    static class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean>{
        @Override
        protected void reduce(Text phoneNum, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
            long sumUpFlow = 0;
            long sumDownFlow = 0;
           for(FlowBean flowBean : values){
               sumUpFlow += flowBean.getUpFlow();
               sumDownFlow += flowBean.getDownFlow();
           }
           context.write(phoneNum, new FlowBean(sumUpFlow, sumDownFlow));
        }
    }
}
