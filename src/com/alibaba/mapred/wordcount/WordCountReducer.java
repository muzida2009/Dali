package com.alibaba.mapred.wordcount;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.Iterator;

/**
 *  mapper 的输出作为输入, key 就是word, value 就是count
 *  reducer自己的输出, key就是word, value就是count
 * Created by Dali on 2018/1/16.
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
    //每调用一次, 其实都是对同一个单词的所有统计数据进行汇总
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //1. 使用迭代器
        int count = 0;
       /* Iterator<IntWritable> iterator = values.iterator();
        while (iterator.hasNext()){
            count += iterator.next().get();
        }*/
        //2. 使用普通方式
        for(IntWritable value : values){
            count += value.get();
        }
        context.write(key, new IntWritable(count));
    }
}
