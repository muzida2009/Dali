package com.ericsson.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * DBCP获取连接的工具类
 * @author zhy
 *
 */
public class DBCPUtil {

	//定义一个数据源对象
	private static DataSource ds;
	
	//使用静态代码块给数据源对象赋值
	static{
		InputStream in = null;
		try {
			Properties prop = new Properties();
			in = DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			prop.load(in);
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError("初始化连接失败！");
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//获取数据源
	public static DataSource getDataSource(){
		return ds;
	}
	
	
	//获取一个连接
	public static Connection getConnection(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
