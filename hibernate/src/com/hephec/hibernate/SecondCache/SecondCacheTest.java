package com.hephec.hibernate.SecondCache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hephec.hibernate.qbc.Employee;

public class SecondCacheTest {


	private SessionFactory sessionFactory=null;
	private Session session=null;
	private Transaction transaction=null;
	@Before
	public void before(){
		
		Configuration config=new Configuration().configure();
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		sessionFactory=config.buildSessionFactory(serviceRegistry);
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
	}
	
	@After
	public void after(){
		transaction.commit();
		session.close();
		sessionFactory.close();
		
	}
	
	@Test
	public void test(){
		//一级缓存
		Employee emp=(Employee) session.get(Employee.class, 1);
		System.out.println(emp);
		
		transaction.commit();
		session.close();
		
		Employee emp1=(Employee) session.get(Employee.class, 1);
		System.out.println(emp1);
		//二级缓存保证的是当session关闭后也只发送一条SQL语句
		
		/**
		 * 1.SessionFactory的缓存可以分为两类
		 * -内置缓存：hibernate自带的，不可卸载的通过在Hibernate的初始化阶段,Hib
		 * Hibernate会把映射元数据和预定义的SQL于菊芳放到SessionFactory的缓存中，映射元首映射文件中
		 * 的数据(.hbm,xml文件中的数据)的复制,该内置缓存是只读的
		 * -外置缓存(二级缓存)：一个可配置的缓存插件,默认情况下，SessioNFactor不胡启用该缓存插件
		 * 外置缓存中的数据是数据库数据的赋值，外置缓存的物理介质可以是内存或者硬盘
		 * 
		 * */
		
		
	}
}
