package com.ericsson.factory;

import com.ericsson.service.AccountService;
import com.ericsson.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {
	public static AccountService getAccountService( AccountService accoutServiceSrc){
		final AccountService accoutService =  accoutServiceSrc;
		AccountService proxy = (AccountService)Proxy.newProxyInstance(accoutService.getClass().getClassLoader(), accoutService.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				 	/*1. 开启事务
				 	2. 提交事务
				 	3. 回滚事务
				 	4. 释放资源*/
				Object obj = null;
				try {
					TransactionManager.beginTransaction();
					method.invoke(accoutService, args);
					TransactionManager.commit();
				} catch (Exception e) {
					System.out.println("Account Exception , failed to transfer !");
					e.printStackTrace();
					TransactionManager.rollback();
				} finally {
					TransactionManager.release();
				}
				return obj;

				/*return method.invoke(accoutService, args);*/
			}
		});
		return proxy;
	}
}
