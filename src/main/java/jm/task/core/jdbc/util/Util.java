package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    public static Connection connectionJDBC = getConnectionJDBC();

    public static Connection getConnectionJDBC() {
        try {
            Connection connection = DriverManager.getConnection(Constants.DB_URL,
                    Constants.DB_USER_NAME, Constants.DB_PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.err.println("Error connecting to the database:");
            e.printStackTrace();
            return null;
        }
    }

    public static SessionFactory sessionFactory = new Configuration()
            .addProperties(getPropertiesSessionFactory())
            .addAnnotatedClass(User.class)
            .buildSessionFactory();

    private static Properties getPropertiesSessionFactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", Constants.DB_URL);
        properties.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.connection.username", Constants.DB_USER_NAME);
        properties.setProperty("hibernate.connection.password", Constants.DB_PASSWORD);
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        return properties;
    }
}