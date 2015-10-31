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
		
		//����ԭ��SQL��ѯ
		String sql="insert into gg_department values(?,?)";
		Query query=session.createQuery(sql);
		query.setInteger(0, 100).setString(1, "hephec").executeUpdate();		
		//HQLҳ֧��ɾ�����޸ĵĲ���
		
		
	}
	@Test
	public void testQBC(){
		
		//1.����һ��criteria����
		Criteria criteria=session.createCriteria(Employee.class);
		
		//2.��Ӳ�ѯ����
		//Criteria����ͨ��Restrictions����ľ�̬�����õ�
		criteria.add(Restrictions.eq("email", "hephec@sina.com"));
		criteria.add(Restrictions.eq("salary", 1000));
		
		
		//3.ִ�в�ѯ
		Employee emp=(Employee) criteria.uniqueResult();
		System.out.println(emp);
		
		//ͳ�Ʋ�ѯ
		Criteria criteriaCount=session.createCriteria(Employee.class);
		//ʹ��Projection����ʾ,������Projections�ľ�̬�����õ�
		criteriaCount.setProjection(Projections.max("salary"));
		System.out.println(criteriaCount.uniqueResult());
		
		//����
		Criteria criteriaSort=session.createCriteria(Employee.class);
		//�������
		criteriaSort.addOrder(Order.asc("salary"));
		criteriaSort.addOrder(Order.desc("email"));
		//��ӷ�ҳ����
		int pageSize=5;
		int pageNo=3;
		criteriaSort.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
		
	}
	@Test
	public void testQBCAndOr(){
		
		//1.����һ��criteria����
		Criteria criteria=session.createCriteria(Employee.class);
		//and,Conjunction�������һ��Criterion����,�����л��������Criterion����
		Conjunction conjunction=Restrictions.conjunction();
		conjunction.add(Restrictions.like("name", "a",MatchMode.ANYWHERE));
		
		System.out.println(criteria);
		
		
	}
}
