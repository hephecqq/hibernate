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
		//һ������
		Employee emp=(Employee) session.get(Employee.class, 1);
		System.out.println(emp);
		
		transaction.commit();
		session.close();
		
		Employee emp1=(Employee) session.get(Employee.class, 1);
		System.out.println(emp1);
		//�������汣֤���ǵ�session�رպ�Ҳֻ����һ��SQL���
		
		/**
		 * 1.SessionFactory�Ļ�����Է�Ϊ����
		 * -���û��棺hibernate�Դ��ģ�����ж�ص�ͨ����Hibernate�ĳ�ʼ���׶�,Hib
		 * Hibernate���ӳ��Ԫ���ݺ�Ԥ�����SQL�ھշ��ŵ�SessionFactory�Ļ����У�ӳ��Ԫ��ӳ���ļ���
		 * ������(.hbm,xml�ļ��е�����)�ĸ���,�����û�����ֻ����
		 * -���û���(��������)��һ�������õĻ�����,Ĭ������£�SessioNFactor�������øû�����
		 * ���û����е����������ݿ����ݵĸ�ֵ�����û����������ʿ������ڴ����Ӳ��
		 * 
		 * */
		
		
	}
}
