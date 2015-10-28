package com.hephec.hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionIntro {
	/**
	 * Hibernate_Session 概述
	 * 通过Session操作数据库
	 * 保存,更新,删除和加载java对象的方法
	 * 
	 * */
	//Session具有一个缓存(以及缓存)
	//Hibernate把对象分为4种状态
	//生成环境不能作为成员变量，可能有并发的问题
	
	private SessionFactory sessionFactory=null;
	private Session session=null;
	private Transaction transaction=null;
	
	
	@Test
	public void test(){
		Configuration config=new Configuration().configure();
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		sessionFactory=config.buildSessionFactory(serviceRegistry);
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		
		//System.out.println("test");
		
	}
	@Before
	public void init(){
		//System.out.println("init...");
		
	}
	@After
	public void destroy(){
		//System.out.println("destroy...");
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
}
