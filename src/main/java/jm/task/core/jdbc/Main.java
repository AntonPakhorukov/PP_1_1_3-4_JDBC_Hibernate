package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
//        userService.dropUsersTable();

//        userService.createUsersTable();

        userService.saveUser("Garry", "Potter" , (byte) 14);
        userService.saveUser("Ron", "Uisley" , (byte) 14);
        userService.saveUser("Germiona", "Granger" , (byte) 15);
        userService.saveUser("Severus", "Snape" , (byte) 45);
        userService.saveUser("Tom", "Radle" , (byte) 54);
//        System.out.println();

        userService.getAllUsers();

//        userService.removeUserById(1);
//        userService.getAllUsers();
//        userService.cleanUsersTable();
//        userService.getAllUsers();
//        userService.dropUsersTable();
    }
}
