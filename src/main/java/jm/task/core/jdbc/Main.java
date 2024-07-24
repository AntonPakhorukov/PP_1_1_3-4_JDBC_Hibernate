package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();

        userDao.saveUser("Garry", "Potter", (byte) 14);
        userDao.saveUser("Ron", "Uisley", (byte) 14);
        userDao.saveUser("Germiona", "Granger", (byte) 15);
        userDao.saveUser("Severus", "Snape", (byte) 45);

        userDao.removeUserById(3);

        userDao.getAllUsers();

        userDao.cleanUsersTable();

        userDao.dropUsersTable();
    }
}
