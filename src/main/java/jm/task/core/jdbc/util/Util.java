package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USERNAME = "KataRoot";
    private static final String PASSWORD = "root";

    /**
     * JDBC
     * @return Соединение с БД
     * @throws SQLException Ошибка соединения
     */
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//    }

    /**
     * Hibernate
     */

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = getConfiguration();
                config.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties()).build();
                sessionFactory = config.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.err.println("Problem creating session factory");
                System.err.println(e.getMessage());
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration config = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, DRIVER);
        settings.put(Environment.URL, URL);
        settings.put(Environment.USER, USERNAME);
        settings.put(Environment.PASS, PASSWORD);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        config.setProperties(settings);
        return config;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }


//    private static final SessionFactory sessionFactory = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            return new Configuration().configure().buildSessionFactory();
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            throw new ExceptionInInitializerError(e);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        getSessionFactory().close();
//    }

//    private static final SessionFactory sessionFactory = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            Configuration configuration = getConfiguration();
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties()).build();
//            return configuration.buildSessionFactory(serviceRegistry);
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    private static Configuration getConfiguration() {
//        Configuration configuration = new Configuration();
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        configuration.setProperty("hibernate.driver_class", "com.mysql.cj.jdbc.Driver");
//        configuration.setProperty("hibernate.url", URL);
//        configuration.setProperty("hibernate.username", USERNAME);
//        configuration.setProperty("hibernate.password", PASSWORD);
//        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
//        return configuration;
//    }
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//    public static void shutdown() {
//        getSessionFactory().close();
//    }

}
