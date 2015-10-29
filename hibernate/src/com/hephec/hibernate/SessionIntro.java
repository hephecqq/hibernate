package com.hephec.hibernate;


import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hephec.hibernate.entity.News;

public class SessionIntro {
	
	@Test
	public void evict(){
		//��session�����а�ָ���ĳ־û������Ƴ�
		News news1=(News) session.get(News.class, 1004);
		News news2=(News) session.get(News.class, 1004);
		
		news1.setAuthor("aa");
		news2.setAuthor("bb");
		session.evict(news1);
		
	}
	@Test
	public void testMerge(){
		//JPAʱ�ټ�
	}
	@Test
	public void testTrigger(){
		//��hibernate�봥����Эͬ����
		
	}
	
	@Test
	public void testDoWork(){
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection);
				//���ô洢����
			}
		});
	}
	@Test
	public void testDelete(){
		//ִ��ɾ��ɾ��,ֻ��OID�����ݱ��е�һ����¼��Ӧ���ͻ�׼��ִ��delete����
		//��OID�����ݱ���û�ж�Ӧ�ļ�¼�����׳��쳣
		News news=new News();
		//news.setId(10011); ɾ�������ڵĶ����׳��쳣
		session.delete(news);
		News news2=(News) session.get(News.class, 1005);
		session.delete(news2);
		//��flush����ʱɾ����
		//ͨ������hibernate�����ļ�hibernate.use_identifier_rollbackΪtrue
		
		
	}
	/**
	 * 1.������һ���־û����󣬲���Ҫ��ʾ����update()��������Ϊ��
	 * ����Transaction��commit()����ʱ������ִ��session��flush����
	 * 
	 * */
	@Test
	public void testUpdate(){
		News news=(News) session.get(News.class, 1005);
//		transaction.commit();
//		session.close();
//		session=sessionFactory.openSession();
//		transaction=session.beginTransaction();
//		
		//���԰�һ����������Ϊ�־û�����
		news.setAuthor("zhangsan");
		//session.update(news);
		
		/**
		 * 1.��ʹ�ö���û�б仯�����ø÷������ᷢ��update()���
		 * 2.�ʹ�����Эͬ����ʱ���ܻᷢ������
		 * 3.���������
		 * 	�������update()��������äĿ����update()��䣬���ڸ���֮ǰ
		 * 	��ѯ���ݿ�
		 * ��hbm.xml�ļ�������select-before-update="true"����
		 * ֻ���ڸ�������Э����ʱ�����ø�����
		 * 4.�����һ�����ݱ���Ķ��󣬵���update()�������׳��쳣
		 * 5.
		 * 
		 * */
	}

	
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
	public void get(){
		//get()��������һ�������ڴ�
		News news=(News) session.get(News.class, 10905);
		System.out.println(news);
		//����get()���������Ϸ���һ��sql����ѯ����
		System.out.println(news.getClass().getName());
		//���ö��󲻴��ڵĻ�������һ��null
	}

	@Test
	public void load(){
		//get()��������һ�������ڴ�
		News news=(News) session.load(News.class, 10095);
		//System.out.println(news.getAuthor());
		//���ø÷�����û�����ϲ�ѯ�����Ƿ���һ���������
		System.out.println(news.getClass().getName());
		//�����⣺
		/**
		 * 1.ִ��get()�������������ض���
		 * 2.��ִ��load()����������ʹ�øö����򲻻�ִ�в�ѯ������������һ���������
		 * 3.load()����ʹ���ӳټ���,ʹ��ʱ�ڼ���
		 * get������������load���ӳټ���
		 * 4.����Ҫ��ʼ���������֮ǰ�ر���Session�����׳��������쳣
		 * 5.����ʹ�øö�����κ�����û���⣬�����׳��쳣
		 * 6.��update()��������һ���������ʱ�������session�еĻ�����
		 * �Լ����ڵ�����ͬ��OID�ĳ־û���������׳��쳣����Ϊ��session�����в���������OID��ͬ�Ķ���
		 * 
		 * */
		
		//���÷����������ǣ��׳�һ��ObjectNotFoundException�쳣
		
	}
	
	@Test
	public void testSave(){
		//save()��������һ����ʱ�����Ϊ�־û�����ͬʱ���������ݿ���
		News news=new News();
		news.setAuthor("sic");
		news.setDate(new java.sql.Date(new java.util.Date().getTime()));
		news.setTitle("Oracle11");
		System.out.println(news);
		//news.setId(1000);
		//session.save(news);
		
		news.setId(10000);
		session.persist(news);
		
		System.out.println(news);
		/**
		 * 1.save:ִ��save()������Ϊ�������ID
		 * 2.��flush����ʱ��ƻ�ִ��һ��insert���
		 * 3.ID:��save()����֮ǰ��ID����Ч��
		 * 4.��save()����֮��־û������ID�ǲ��ܱ��޸ĵ�
		 * */
		
		/**
		 * persist()����֮ǰ������Ӧ�Ѿ�����id��,�򲻻�ִ��insert�������׳��쳣
		 * 
		 * */
	}
	@Test
	public void saveOrUpdate(){
		//�жϱ�׼����������OID�Ƿ�Ϊ��
		//��OID��Ϊnull,ִ�и÷������׳�һ���쳣
		//OIDֵ==id��unsaved-value����ֵ�Ķ���Ҳ����Ϊ���������
		
		
	}
	@Test
	public void testState(){
		//��ʱ���� OIDΪ��,���ٻ�����,���ݿ��޶�Ӧ��¼
		
		//�־û�����  OID��Ϊ��,λ��Session����,���ݿ����ж�Ӧ�ļ�¼,
		//����flush()����ʱ������ݳ־û���������Ա仯����ͬ���������ݿ�
		//������ֻ��һ���־û�����
		
		//ɾ������ 
		
		//�������
		
		
	}
	
	
	@Test
	public void testClear(){
		
		//clear()������������
		News news1=(News) session.get(News.class, 1001);
		System.out.println(news1);
		session.clear();
		News news2=(News) session.get(News.class, 1001);
		System.out.println(news2);
	}
	
	@Test
	public void test(){
		
		/**
		 * ͨ��Session��������
		 * Hibernate_Session ����(Hibernate һ������)
		 * 
		 * */
		//System.out.println("test");
		News news=(News) session.get(News.class, 1001);
		System.out.println(news);
		News news1=(News) session.get(News.class, 1001);
		System.out.println(news1);
		
		//����ֻ�ᷢ��һ��sql���
		//Session�������һЩ��Java����
		//��ζ�session������в���
		//1.flush()����
		
		//2.reflesh()����
		
		//3.clear()����
		
	}
	@Test
	public void testFlush(){
		News news=(News) session.get(News.class, 1001);
		System.out.println(news);
		//����session������������,����һ������,Session���Ը�֪��
		news.setAuthor("hecheng");
		session.flush();
		/**
		 * flushʹ���ݱ��еļ�¼��session�����еĶ����״̬����һ��
		 * Ϊ�˱���һ������ܻᷢ�Ͷ�Ӧ��sql���
		 * 1.����Transaction��commit()�����У�
		 * �ڸ÷������Ƚ���flush()����,Ȼ�����ύ����
		 * 2.flush()�����������ܻᷢ��sql��䣬�������ύ����
		 * 3.��ʾflush()���� 
		 * 
		 * */
		
		//session.get(News.class, 1001);
		News news1=(News) session.createCriteria(News.class).uniqueResult();
		System.out.println(news1);
		
		
		
	}
	
	@Test
	public void testFlush2(){
		News news=new News("java","sun",new java.sql.Date(new java.util.Date().getTime()));
		
		session.save(news);
		
		
	}
	
	@Test
	public void testReflesh(){
		News news=(News) session.get(News.class, 1001);
		System.out.println(news);
		session.refresh(news);
		System.out.println(news);
		//reflesh()������
		/**
		 * ��ǿ�Ʒ���select�����ʹSession�����ж����״̬�����ݱ��ж�Ӧ�ļ�¼����һ��
		 *	���ݿ�ĸ��뼶�����⣺
		 * Hibernate�����ø��뼶��
		 * 1,2,4,8
		 * 
		 * */
		
	}
	
	@Before
	public void init(){
		//System.out.println("init...");
		Configuration config=new Configuration().configure();
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		sessionFactory=config.buildSessionFactory(serviceRegistry);
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		//��δ�ύ�������ʾ����session.flush()����֮ǰҲ�п��ܵ���flush����
		//1.ִ��HQL����QBC��ѯ,���Ƚ���flush����,�Եõ����ݱ����µļ�¼
		//2.����¼��ID���ɵײ������ķ�ʽ���ɵģ����ڵ���save()����֮�����������insert���
		//����save()��������뱣֤�����ID�Ǵ��ڵ�
		
		
	
	}
	@After
	public void destroy(){
		//System.out.println("destroy...");
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
}
