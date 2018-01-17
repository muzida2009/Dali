package com.alibaba.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Dynamic proxy based on cglib
 * Created by Dali on 2018/1/8.
 */
public class Demo02 {
    /**
     *  1. proxy proxy object
     *  2. Mmethod ethod will be ran
     *  3. params needed while running
     *  4. methodProxy
     */
    private Alto alto;
    @Before
    public void init(){
        alto = (Alto)Enhancer.create(Alto.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if ("run".equals(method.getName())){
                    System.out.println("running in speed 200KM/s");
                }
                return null;
            }
        });
    }
   @Test
    public void testCglib(){
       alto.run("lida");
   }
}
