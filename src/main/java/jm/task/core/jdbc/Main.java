package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.dropUsersTable();

        userDao.createUsersTable();

        userDao.saveUser("Garry", "Potter" , (byte) 14);
        userDao.saveUser("Ron", "Uisley" , (byte) 14);
        userDao.saveUser("Germiona", "Granger" , (byte) 15);
        userDao.saveUser("Severus", "Snape" , (byte) 45);
        System.out.println();
        userDao.getAllUsers();
//        userDao.removeUserById(2);
//        userDao.getAllUsers();
//        userDao.cleanUsersTable();
//        userDao.getAllUsers();
//        userDao.dropUsersTable();
    }
}
