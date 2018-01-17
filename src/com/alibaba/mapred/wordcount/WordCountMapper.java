package com.alibaba.mapred.wordcount;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
/**
 * Created by Dali on 2018/1/16.
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
       /*
          1. key mr框架所读到的一行文本的其实偏移量,此处没有用上 输入
          2. value, mr框架所读到的一行文本的内容 输入
          3. 将单词作为key, 单词技术作为value输出
        */
        String line = value.toString();
        String[] words = line.split(" ");
        for(String word: words){
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
