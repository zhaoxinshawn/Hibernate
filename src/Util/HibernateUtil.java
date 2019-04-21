package Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static SessionFactory factory;
    static {
        Configuration configuration=new Configuration().configure();
        factory = configuration.buildSessionFactory();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("结束！");
                factory.close();
            }
        });

    }
    public static Session openSession(){
        return factory.openSession();
    }
    public static Session getCurrentSession(){
        return factory.getCurrentSession();
    }

}
