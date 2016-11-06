package pool.jdbc.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

/**
 * Description: 数据库连接池�?
 * 
 * @filename DataSourceFactory.java
 * @date 2013�?�?1�?19:47:21
 * @author Herman.Xiong
 * @version 1.0 Copyright (c) 2013 Company,Inc. All Rights Reserved.
 */
public class DbcpDataSourceFactory {
    private static Logger log = Logger.getLogger(DbcpDataSourceFactory.class);
    private static BasicDataSource bs = null;
    public static String driver = "";
    public static String url = "";
    public static String userName = "";
    public static String password = "";

    public static void main(String args[]) {
    }

    /**
     * 创建数据�?
     * 
     * @return
     */
    public static BasicDataSource getDataSource() throws Exception {
	if (bs == null) {
	    log.info("数据库连接信息：[driver:" + driver + ",url:" + url + ",userName:"
		    + userName + ",password:" + password + "]");
	    bs = new BasicDataSource();
	    bs.setDriverClassName(driver);
	    bs.setUrl(url);
	    bs.setUsername(userName);
	    bs.setPassword(password);
	    bs.setMaxActive(200);// 设置�?��并发�?
	    bs.setInitialSize(30);// 数据库初始化时，创建的连接个�?
	    bs.setMinIdle(50);// �?��空闲连接�?
	    bs.setMaxIdle(200);// 数据库最大连接数
	    bs.setMaxWait(1000);
	    bs.setMinEvictableIdleTimeMillis(60 * 1000);// 空闲连接60秒中后释�?
	    bs.setTimeBetweenEvictionRunsMillis(5 * 60 * 1000);// 5分钟�?���?��是否有死掉的线程
	    bs.setTestOnBorrow(true);
	}
	return bs;
    }

    /**
     * 释放数据�?
     */
    public static void shutDownDataSource() throws Exception {
	if (bs != null) {
	    bs.close();
	}
    }

    /**
     * 获取数据库连�?
     * 
     * @return
     */
    public static Connection getConnection() {
	Connection con = null;
	try {
	    if (bs != null) {
		con = bs.getConnection();
	    } else {
		con = getDataSource().getConnection();
	    }
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	}
	return con;
    }

    /**
     * 关闭连接
     */
    public static void closeCon(ResultSet rs, PreparedStatement ps,
	    Connection con) {
	if (rs != null) {
	    try {
		rs.close();
	    } catch (Exception e) {
		log.error("关闭结果集ResultSet异常�?+e.getMessage(), e");
	    }
	}
	if (ps != null) {
	    try {
		ps.close();
	    } catch (Exception e) {
		log.error("预编译SQL语句对象PreparedStatement关闭异常�?+e.getMessage(), e");
	    }
	}
	if (con != null) {
	    try {
		con.close();
	    } catch (Exception e) {
		log.error("关闭连接对象Connection异常�?+e.getMessage(), e");
	    }
	}
    }
}
