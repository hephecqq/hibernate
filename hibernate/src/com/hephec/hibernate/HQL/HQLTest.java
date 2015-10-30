package com.hephec.hibernate.HQL;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hephec.hibernate.mapping.Address;

public class HQLTest {

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
		/**
		 * 1.创建Query对象
		 * 
		 * 2.绑定参数
		 * 
		 * 3.执行查询
		 * */
//		String hql="from Address";
//		Query query=session.createQuery(hql);
//		List<Address> emps=query.list();
//		for (int i = 0; i < emps.size(); i++) {
//			System.out.println(emps.get(i).getAddressName());
//		}
		
	
		String hql="from Address as A where A.addressID <?";
		Query query=session.createQuery(hql);
		query.setInteger(0, 4);
		List<Address> emps=query.list();
		for (int i = 0; i < emps.size(); i++) {
			System.out.println(emps.get(i).getAddressName());
		}
		System.out.println(emps.size());
		
	}
	
	public List<Address> testFindCustomerByName(String name){
		return session.createQuery("from Customer as c left join fetch c.orders where c.name="+name)
							.list();
	}
}
