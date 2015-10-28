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
		//Hibernate_HelloWorld ����ʵ�ֲ���
		//1.����һ��SessionFactory����
		Configuration config=new Configuration().configure();
		//֮ǰ�ķ�ʽ
		//factory=config.buildSessionFactory();
		//1.2����һ��ServiceRegistry����:hibernate�¼ӵĶ��󣬷����ע����
		//����Hibernate�κ����úͷ�����Ҫ�ڸö�����ע��������Ч
		
		ServiceRegistry serviceRegistry=
				new ServiceRegistryBuilder().
					applySettings(config.getProperties()).
						buildServiceRegistry();
		
		factory=config.buildSessionFactory(serviceRegistry);
	
		//2.����һ��Session����
		Session session=factory.openSession();
		//3.��������
		transaction=session.beginTransaction();
		//4.ִ�б������
		News news=new News("java","hephec",new java.sql.Date(new java.util.Date().getTime()));
		session.save(news);
		News news1=(News) session.get(News.class, 1);
		System.out.println(news1);
		//5.�ύ����
		transaction.commit();
		//6.�ر�Session����
		session.close();
		//7.�ر�SessionFactory����
		factory.close();
	}
}
