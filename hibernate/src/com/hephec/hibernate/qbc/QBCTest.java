package com.hephec.hibernate.qbc;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QBCTest {


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
	public void testNativeSQL(){
		
		//本地原生SQL查询
		String sql="insert into gg_department values(?,?)";
		Query query=session.createQuery(sql);
		query.setInteger(0, 100).setString(1, "hephec").executeUpdate();		
		//HQL页支持删除了修改的操作
		
		
	}
	@Test
	public void testQBC(){
		
		//1.创建一个criteria对象
		Criteria criteria=session.createCriteria(Employee.class);
		
		//2.添加查询条件
		//Criteria可以通过Restrictions对象的静态方法得到
		criteria.add(Restrictions.eq("email", "hephec@sina.com"));
		criteria.add(Restrictions.eq("salary", 1000));
		
		
		//3.执行查询
		Employee emp=(Employee) criteria.uniqueResult();
		System.out.println(emp);
		
		//统计查询
		Criteria criteriaCount=session.createCriteria(Employee.class);
		//使用Projection来表示,可以有Projections的静态方法得到
		criteriaCount.setProjection(Projections.max("salary"));
		System.out.println(criteriaCount.uniqueResult());
		
		//排序
		Criteria criteriaSort=session.createCriteria(Employee.class);
		//添加排序
		criteriaSort.addOrder(Order.asc("salary"));
		criteriaSort.addOrder(Order.desc("email"));
		//添加翻页方法
		int pageSize=5;
		int pageNo=3;
		criteriaSort.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
		
	}
	@Test
	public void testQBCAndOr(){
		
		//1.创建一个criteria对象
		Criteria criteria=session.createCriteria(Employee.class);
		//and,Conjunction本身就是一个Criterion对象,且其中还可以添加Criterion对象
		Conjunction conjunction=Restrictions.conjunction();
		conjunction.add(Restrictions.like("name", "a",MatchMode.ANYWHERE));
		
		System.out.println(criteria);
		
		
	}
}
