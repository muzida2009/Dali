package com.alibaba.proxy;

import org.junit.Test;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.InvokeHandler;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * Created by Dali on 2018/1/5.
 */
public class Demo01 {

    @Test
    public void test(){
        final Alto alto = new Alto();
        Car altoProxy = (Car)Proxy.newProxyInstance(Alto.class.getClassLoader(), new Class[]{Car.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = method.invoke(alto, args);
                return result;
            }
        });
        //test
        int startTime = altoProxy.start("97#");
        int driver = altoProxy.run("lida");
        int stopTime = altoProxy.stop();
        System.out.println(" startTime is : " + startTime);
        System.out.println(" The driver name is : " + driver);
        System.out.println(" stopTime is : " + stopTime);
    }
}