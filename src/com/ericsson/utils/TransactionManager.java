package com.ericsson.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 这是一个事务管理的工具类
 * @author zhy
 *
 */
public class TransactionManager {

	//1.定义一个线程局部变量
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	//2.获取连接：以后获取连接，只能使用该方法
	public static Connection getConnection(){
		//获取当前线程上，是否已经绑定了一个连接
		Connection conn = tl.get();
		if(conn == null){
			//当前线程上还没用绑定
			tl.set(DBCPUtil.getConnection());//绑定一个
		}
		return tl.get();
	}
	
	//3.开启事务
	public static void beginTransaction(){
		//拿到当前线程上的连接
		Connection conn = getConnection();
		//开启事务
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//4.提交事务
	public static void commit(){
		//拿到当前线程上的连接
		Connection conn = getConnection();
		//提交事务
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//5.回滚事务
	public static void rollback(){
		//拿到当前线程上的连接
		Connection conn = getConnection();
		//回滚事务
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//6.释放资源 还回池中
	public static void release(){
		//拿到当前线程上的连接
		Connection conn = getConnection();
		//回滚事务
		try {
			conn.close();//连接还回池中
			tl.remove();//把当前线程和连接进行解绑。因为服务器用的是线程池技术
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
