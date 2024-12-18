package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // Константы для подключения к базе данных
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";

    // Единственный экземпляр SessionFactory
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Настройка свойств Hibernate
            Properties hibernateProperties = new Properties();
            hibernateProperties.put(Environment.DRIVER, "org.postgresql.Driver");
            hibernateProperties.put(Environment.URL, DB_URL);
            hibernateProperties.put(Environment.USER, DB_USERNAME);
            hibernateProperties.put(Environment.PASS, DB_PASSWORD);
            hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            hibernateProperties.put(Environment.SHOW_SQL, "true");
            hibernateProperties.put(Environment.FORMAT_SQL, "true");
            hibernateProperties.put(Environment.HBM2DDL_AUTO, "update");

            // Создание SessionFactory
            sessionFactory = new Configuration()
                    .setProperties(hibernateProperties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Ошибка при создании SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Метод для получения соединения JDBC
     * @return Connection - объект соединения с базой данных
     * @throws RuntimeException если соединение не удалось установить
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println("Ошибка при установке соединения с базой данных: " + e.getMessage());
            throw new RuntimeException("Не удалось установить соединение с базой данных", e);
        }
    }

    /**
     * Метод для получения SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Метод для закрытия SessionFactory при завершении работы приложения
     */
    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}