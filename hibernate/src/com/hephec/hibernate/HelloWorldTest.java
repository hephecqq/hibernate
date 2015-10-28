package com.hephec.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import com.hephec.hibernate.entity.News;

public class HelloWorldTest {

	SessionFactory factory=null;
	Session session=null;
	Transaction transaction=null;
	@Test
	public void test(){
		//Hibernate_HelloWorld 代码实现部分
		//1.创建一个SessionFactory对象
		Configuration config=new Configuration().configure();
		//之前的方式
		//factory=config.buildSessionFactory();
		//1.2创建一个ServiceRegistry对象:hibernate新加的对象，服务的注册器
		//创建Hibernate任何配置和服务都需要在该对象中注册后才能有效
		
		ServiceRegistry serviceRegistry=
				new ServiceRegistryBuilder().
					applySettings(config.getProperties()).
						buildServiceRegistry();
		
		factory=config.buildSessionFactory(serviceRegistry);
	
		//2.创建一个Session对象
		Session session=factory.openSession();
		//3.开启事务
		transaction=session.beginTransaction();
		//4.执行保存操作
		News news=new News("java","hephec",new java.sql.Date(new java.util.Date().getTime()));
		session.save(news);
		News news1=(News) session.get(News.class, 1);
		System.out.println(news1);
		//5.提交事务
		transaction.commit();
		//6.关闭Session对象
		session.close();
		//7.关闭SessionFactory对象
		factory.close();
	}
}
