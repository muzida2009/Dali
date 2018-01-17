package com.alibaba.mapred.flowcount;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
/**
 * Created by Dali on 2018/1/17.
 */
public class FlowBean implements Writable {
    //三个字段: 上传流量, 下载流量, 总流量
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    public FlowBean(){}//实现序列化啊, 需要提供空参构造,反射时用到
    public FlowBean(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }
    //重写read和write方法
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
    }
    @Override
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlow = in.readLong();
    }
    public long getUpFlow() {
        return upFlow;
    }
    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }
    public long getDownFlow() {
        return downFlow;
    }
    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }
    public long getSumFlow() {
        return sumFlow;
    }
    //完成 总流量的计算
    public void setSumFlow(long sumFlow) {
        this.sumFlow = upFlow + downFlow;
    }
    @Override
    public String toString() {
       return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }
}
