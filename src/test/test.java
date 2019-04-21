package test;

import Util.HibernateUtil;
import model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;


public class test {
    @Test
    public void test1(){

        //获取配置文件，默认是src的hibernate.cfg.xm文件
        //Configuration configuration = new Configuration("hibernate.cfg.xml").configure();
        Configuration configuration = new Configuration().configure();

        /*
        *  加载mapping
        *  1
        *  configuration.addResource("model/User.hbm.xml");
        *
        *  2内部会拼接hbm.xml后缀
        *  configuration.addClass(User.class);
        *  */

        //创建sessionFactory，SessionFactory线程安全，多个线程同时访问时，不会出现线程并发访问问题
        SessionFactory sessionFactory =configuration.buildSessionFactory();

        //开启session
        /*
        * 两种方式
        * 1.factory.openSession() 获取一个全新的session
        *
        * 2.factory.getCurrentSession() 获取一个与当前线程绑定的session
            * a.要使用这个方法必须在hibernate.cfg.xml中配置
            * <property name="hibernate.current_session_context_class">thread</property>
            * b.hibernate支持，将创建的session绑定到本地线程中，底层使用ThreadLocal，在程序之间共享session。
            * c.如果提交或者回滚事务，底层将自动关闭session
        * */
        Session session =sessionFactory.openSession();

        /*
        * session的api(方法)
        * 	save 保存
        	get 通过id查询，如果没有 null,get方法是直接加载数据库
        	load 通过id查询，如果没有抛异常,load方法返回的是对象的一个代理,load的设计是懒加载，用到时才去查询数据库
        	update 更新
        	delete 删除
          */
        Transaction transaction=session.getTransaction();
        try {
            //开启事务
            transaction.begin();

            User user = new User();
            user.setName("哈哈哈");
            user.setPassword("89633");
            session.save(user);

            //提交事务
            transaction.commit();

        }catch (Exception e){
            transaction.rollback();
        }

        session.close();
        sessionFactory.close();

    }

    @Test
    //Query 查询对象
    public void test2(){
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory =configuration.buildSessionFactory();
        Session session =sessionFactory.openSession();
        //Transaction transaction=session.getTransaction();
        try {
            //transaction.begin();

            /*//HQL:Hibernate Query Language的缩写,就是Hibernate的查询语言
            Query query =session.createQuery("from User");//User为类名
            //query.setMaxResults(3);
            //query.setFirstResult(3);

            List<User> list =query.list();
            for (User u:list){
                System.out.println(u);
            }*/

            /*//QBC（query by criteria），hibernate提供纯面向对象查询语言，提供直接使用PO对象进行操作。
            //propertyName为User类中的属性名
            Criteria criteria =session.createCriteria(User.class);
            //等于=
            criteria.add(Restrictions.eq("name","哈哈哈"));
            //like
            //criteria.add(Restrictions.like("name","dd"));
            //大于gt，大于等于ge
            //criteria.add(Restrictions.gt("uid",6));
            //小于lt,小于等于le
            //criteria.add(Restrictions.lt("uid",6));

            List<User> list = criteria.list();
            for (User u:list){
                System.out.println(u);
            }*/

            //SQLQuery:使用原生的SQL语句查询
            SQLQuery query = session.createSQLQuery("select * from t_user;");
            //每行数据存储成数组
            List list = query.list();
            for (int i = 0; i <list.size() ; i++) {
                Object[] objects =(Object[])list.get(i);
                for(Object o:objects){
                    System.out.print(o.getClass()+":"+o+"  ||  ");
                }
                System.out.println();


            }


        }catch (Exception e){
            //transaction.rollback();
        }

        session.close();
        sessionFactory.close();

    }
    @Test
    public void test3(){

        Session session= HibernateUtil.openSession();
        Transaction transaction=session.getTransaction();
        try{
            transaction.begin();
            User user=new User();
            user.setName("22002");
            user.setPassword("51313");

            session.save(user);

            transaction.commit();


        }catch (Exception e){
            transaction.rollback();

        }

        session.close();


    }
}
