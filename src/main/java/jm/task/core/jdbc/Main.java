package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.service.UserService;

public class Main {
    public static void main(String[] args) {
        Util.getConnectionJDBC();
        UserService users = new UserServiceImpl();
        users.createUsersTable();

        User user1 = new User("Vasya", "Pupkin", (byte) 31);
        User user2 = new User("Vova", "Hrushin", (byte) 32);
        User user3 = new User("Katya", "Koshka", (byte) 33);
        User user4 = new User("Valya", "Valenok", (byte) 34);

        users.cleanUsersTable();

        users.dropUsersTable();

        Util.sessionFactory.close();
    }
}