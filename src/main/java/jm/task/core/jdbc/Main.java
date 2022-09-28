package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
//        Util.getConnection();

        UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDao1 = new UserDaoHibernateImpl();
//        userDao1.dropUsersTable();
//        System.out.println("OK");
//        userDao1.createUsersTable();
//        System.out.println("done");
//
//
        userDao1.saveUser("Name1", "LastName1", (byte) 20);
        userDao1.saveUser("Name2", "LastName2", (byte) 25);
        userDao1.saveUser("Name3", "LastName3", (byte) 31);
//        userDao.saveUser("Name4", "LastName4", (byte) 38);
//
//          userDao1.removeUserById(2);
//
//        System.out.println(userDao1.getAllUsers());
//        userDao1.cleanUsersTable();
//        userDao.dropUsersTable();


    }

}
