package com.alibaba.proxy;

/**
 * Created by Dali on 2018/1/5.
 */
public class Alto implements Car{
    @Override
    public int start(String oil) {
        System.out.println("烧的"+ oil + "号油, 所以启动神速,3秒破百 !");
        return 3;
    }

    @Override
    public int run(String driver) {
        System.out.println("时速200");
        return 200;
    }

    @Override
    public int stop() {
        System.out.println("5s才停下来");
        return 5;
    }
}
