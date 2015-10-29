package com.hephec.hibernate;

import org.junit.Test;

public class Cfgxml {
	@Test
	public void test(){
		//hibernate.cfg.xml文件深入
		//在hibernate中使用c3p0数据源
		//1.导入jar包
		//2.加入配置项
		//1.c3p0
		
		//2.jdbc.fecth_size
		/**
		 * 实质是调用Statement.setFetchSize()方法设定JDBC
		 * 的Statement读取数据的时候每次从数据库中取出的记录条数
		 * 
		 * */
		//3.jdbc.batch_size
		/**
		 * 设定对数据库进行批量删除，批量更新和批量插入的时候的批次大小，类似与设置缓冲区大小的一些
		 * 
		 * */
	}
}
