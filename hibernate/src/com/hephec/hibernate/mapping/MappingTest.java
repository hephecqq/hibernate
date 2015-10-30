package com.hephec.hibernate.mapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MappingTest {

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
	public void testOne2One(){
		
		Customer c1=new Customer();
		c1.setCustomerName("hephec11");
		Address a1=new Address();
		a1.setAddressName("sichuan11");
		//a1.setCustomer(c1);
		c1.setAddress(a1);
		
//		session.save(c1);
		//Customer cc=(Customer) session.get(Customer.class, 9);
		//System.out.println(cc.getAddress().getAddressName());
		Address aa=(Address) session.get(Address.class, 2);
		System.out.println(aa.getCustomer().getCustomerName());
		/**
		 * ����ֻ��Address.hbm.xml�ļ���ӳ����address����Customer�����˫�����,���ͨ��Address�ܹ�������
		 * Customer,������ͨ��Customer���󵼺���Address�Ƕ���
		 * */
		/**
		 * Ĭ�������:���һ�Ĺ�����ϵ�����ӳټ�������,��һ��һ������ϵ���������������Ӽ�������
		 * 
		 * */
	}
	@Test
	public void testMany2One(){
		
	}
	@Test
	public void testMany2Many(){
		
	}
}
