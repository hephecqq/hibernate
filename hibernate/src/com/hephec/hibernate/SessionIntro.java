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
		//从session缓存中把指定的持久化对象移除
		News news1=(News) session.get(News.class, 1004);
		News news2=(News) session.get(News.class, 1004);
		
		news1.setAuthor("aa");
		news2.setAuthor("bb");
		session.evict(news1);
		
	}
	@Test
	public void testMerge(){
		//JPA时再见
	}
	@Test
	public void testTrigger(){
		//与hibernate与触发器协同工作
		
	}
	
	@Test
	public void testDoWork(){
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection);
				//调用存储过程
			}
		});
	}
	@Test
	public void testDelete(){
		//执行删除删除,只有OID和数据表中的一条记录对应，就会准备执行delete操作
		//若OID在数据表中没有对应的记录，则抛出异常
		News news=new News();
		//news.setId(10011); 删除不存在的对象，抛出异常
		session.delete(news);
		News news2=(News) session.get(News.class, 1005);
		session.delete(news2);
		//在flush缓存时删除的
		//通过设置hibernate配置文件hibernate.use_identifier_rollback为true
		
		
	}
	/**
	 * 1.若更新一个持久化对象，不需要显示调用update()方法，因为在
	 * 调用Transaction的commit()方法时，会先执行session的flush方法
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
		//可以把一个游离对象变为持久化对象
		news.setAuthor("zhangsan");
		//session.update(news);
		
		/**
		 * 1.即使该对象没有变化，调用该方法都会发送update()语句
		 * 2.和触发器协同工作时可能会发生问题
		 * 3.解决方案？
		 * 	如何能让update()方法不再盲目触发update()语句，得在更新之前
		 * 	查询数据库
		 * 在hbm.xml文件中设置select-before-update="true"即可
		 * 只有在跟触发器协工作时才设置该属性
		 * 4.如果对一个数据表不存的对象，调用update()方法会抛出异常
		 * 5.
		 * 
		 * */
	}

	
	/**
	 * Hibernate_Session 概述
	 * 通过Session操作数据库
	 * 保存,更新,删除和加载java对象的方法
	 * 
	 * */
	//Session具有一个缓存(以及缓存)
	//Hibernate把对象分为4种状态
	//生成环境不能作为成员变量，可能有并发的问题
	
	private SessionFactory sessionFactory=null;
	private Session session=null;
	private Transaction transaction=null;
	
	@Test
	public void get(){
		//get()方法加载一个对象到内存
		News news=(News) session.get(News.class, 10905);
		System.out.println(news);
		//调用get()方法会马上发送一条sql语句查询对象
		System.out.println(news.getClass().getName());
		//若该对象不存在的话，返回一个null
	}

	@Test
	public void load(){
		//get()方法加载一个对象到内存
		News news=(News) session.load(News.class, 10095);
		//System.out.println(news.getAuthor());
		//调用该方法并没有马上查询，而是返回一个代理对象
		System.out.println(news.getClass().getName());
		//面试题：
		/**
		 * 1.执行get()方法会立即加载对象
		 * 2.而执行load()方法若若不使用该对象，则不会执行查询操作，而返回一个代理对象
		 * 3.load()方法使用延迟加载,使用时在加载
		 * get是立即检索，load是延迟检索
		 * 4.在需要初始化队里对象之前关闭了Session，会抛出懒加载异常
		 * 5.若不使用该对象的任何属性没问题，不会抛出异常
		 * 6.当update()方法关联一个游离对象时，如果在session中的缓存中
		 * 以及存在的了相同的OID的持久化对象，则会抛出异常，因为在session缓存中不能有两个OID相同的对象
		 * 
		 * */
		
		//若该方法不存在是，抛出一个ObjectNotFoundException异常
		
	}
	
	@Test
	public void testSave(){
		//save()方法，把一个临时对象变为持久化对象，同时保存在数据库中
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
		 * 1.save:执行save()方法后为对象分配ID
		 * 2.在flush缓存时会计划执行一条insert语句
		 * 3.ID:在save()方法之前的ID是无效的
		 * 4.在save()方法之后持久化对象的ID是不能被修改的
		 * */
		
		/**
		 * persist()方法之前，若对应已经有了id了,则不会执行insert，而会抛出异常
		 * 
		 * */
	}
	@Test
	public void saveOrUpdate(){
		//判断标准是这个对象的OID是否为空
		//若OID不为null,执行该方法则抛出一个异常
		//OID值==id的unsaved-value属性值的对象，也被认为是游离对象
		
		
	}
	@Test
	public void testState(){
		//临时对象 OID为空,不再缓存中,数据库无对应记录
		
		//持久化对象  OID不为空,位于Session缓存,数据库中有对应的记录,
		//调用flush()方法时，会根据持久化对象的属性变化，来同步更新数据库
		//缓存中只有一个持久化对象
		
		//删除对象 
		
		//游离对象
		
		
	}
	
	
	@Test
	public void testClear(){
		
		//clear()方法会清理缓存
		News news1=(News) session.get(News.class, 1001);
		System.out.println(news1);
		session.clear();
		News news2=(News) session.get(News.class, 1001);
		System.out.println(news2);
	}
	
	@Test
	public void test(){
		
		/**
		 * 通过Session操作对象
		 * Hibernate_Session 缓存(Hibernate 一级缓存)
		 * 
		 * */
		//System.out.println("test");
		News news=(News) session.get(News.class, 1001);
		System.out.println(news);
		News news1=(News) session.get(News.class, 1001);
		System.out.println(news1);
		
		//两次只会发送一条sql语句
		//Session缓存包含一些列Java集合
		//如何对session缓存进行操作
		//1.flush()方法
		
		//2.reflesh()方法
		
		//3.clear()方法
		
	}
	@Test
	public void testFlush(){
		News news=(News) session.get(News.class, 1001);
		System.out.println(news);
		//是在session的生命周期中,开了一个事务,Session可以感知到
		news.setAuthor("hecheng");
		session.flush();
		/**
		 * flush使数据表中的记录和session缓存中的对象的状态保存一致
		 * 为了保持一致则可能会发送对应的sql语句
		 * 1.调用Transaction的commit()方法中：
		 * 在该方法中先进行flush()方法,然后在提交事务
		 * 2.flush()方法方法可能会发送sql语句，但不会提交事务
		 * 3.显示flush()方法 
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
		//reflesh()方法：
		/**
		 * 会强制发送select语句以使Session缓存中对象的状态和数据表中对应的记录保持一致
		 *	数据库的隔离级别问题：
		 * Hibernate中设置隔离级别
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
		//在未提交事务或显示调用session.flush()方法之前也有可能调用flush方法
		//1.执行HQL或者QBC查询,会先进行flush操作,以得到数据表最新的记录
		//2.若记录的ID是由底层自增的方式生成的，则在调用save()方法之后会立即发送insert语句
		//调用save()方法后必须保证对象的ID是存在的
		
		
	
	}
	@After
	public void destroy(){
		//System.out.println("destroy...");
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
}
