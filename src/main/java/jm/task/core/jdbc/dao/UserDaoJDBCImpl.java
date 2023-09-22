package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS " + Constants.DB_NAME + "." + Constants.DB_TABLE_NAME +
                "(id bigint auto_increment, name text, last_name text, age integer, " +
                "constraint table1_pk primary key (id))";
        try (Connection connection = Util.getConnectionJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlQuery = "DROP TABLE IF EXISTS " + Constants.DB_NAME + "." + Constants.DB_TABLE_NAME;
        try (Connection connection = Util.getConnectionJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sqlQuery = "INSERT INTO " + Constants.DB_NAME + "." + Constants.DB_TABLE_NAME
                + " (name, last_name, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnectionJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sqlQuery = "DELETE FROM " + Constants.DB_NAME + "." + Constants.DB_TABLE_NAME + " WHERE id = ?";
        try (Connection connection = Util.getConnectionJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM " + Constants.DB_NAME + "." + Constants.DB_TABLE_NAME;
        try (Connection connection = Util.getConnectionJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sqlQuery = "DELETE FROM " + Constants.DB_NAME + "." + Constants.DB_TABLE_NAME;
        try (Connection connection = Util.getConnectionJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}