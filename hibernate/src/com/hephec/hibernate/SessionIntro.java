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
	 * Hibernate_Session ����
	 * ͨ��Session�������ݿ�
	 * ����,����,ɾ���ͼ���java����ķ���
	 * 
	 * */
	//Session����һ������(�Լ�����)
	//Hibernate�Ѷ����Ϊ4��״̬
	//���ɻ���������Ϊ��Ա�����������в���������
	
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
