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
	public void testOne2OneByPrimaryKey(){
		
		//按照主键实现关联关系映射
		//Address表的ID字段既是主键,同时也作为外键参照Customer表的主键,即Address表语Customer表共享主键
		/**
		 * 1.在Addresss.hbm.xml文件中使用<one-to-one name="customer" class="Customer" constrained="true"/>
		 * 元素constrained属性为true,表明Address表的ID主键同时作为外键参照Customer表,在该文件中OID使用foreign标识符生成策略
		 *<generator class="foreign">
		 *	<param name="property">customer</param>
		 *</generator> 
		 * */
		
	}
	@Test
	public void testOne2OneByForeignKey(){
		//按照外键实现一对一的关联关系映射
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
		 * 由于只在Address.hbm.xml文件中映射中address对象到Customer对象的双向关联,因此通过Address能够导航到
		 * Customer,而不能通过Customer对象导航到Address是对象
		 * */
		/**
		 * 默认情况下:多对一的关联关系采用延迟检索策略,而一对一关联关系采用迫切左外连接检索策略
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
