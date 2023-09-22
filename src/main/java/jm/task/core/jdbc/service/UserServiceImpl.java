package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl daoJDBC;
    private UserDaoJDBCImpl daoHibernate;

    public UserServiceImpl() {
        this.daoJDBC = new UserDaoJDBCImpl();
        this.daoHibernate = new UserDaoJDBCImpl();
    }
    @Override
    public void createUsersTable() {
        daoHibernate.createUsersTable();
    }
    @Override
    public void dropUsersTable() {
        daoHibernate.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        daoHibernate.saveUser(name, lastName, age);
    }
    @Override
    public void removeUserById(long id) {
        daoHibernate.removeUserById(id);
    }
    @Override
    public List<User> getAllUsers() {
        return daoHibernate.getAllUsers();
    }
    @Override
    public void cleanUsersTable() {
        daoHibernate.cleanUsersTable();
    }
}
