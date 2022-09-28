package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    Connection connection = getConnection();

    @Override
    public void createUsersTable() {
        {
            PreparedStatement preparedStatement = null;
            String create = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)";
            try {
                preparedStatement = connection.prepareStatement(create);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        String create =
                "DROP TABLE users;";
        try {
            preparedStatement = connection.prepareStatement(create);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            ;
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {

                session.delete(user);
                System.out.println("student 1 is deleted");
            }

            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<User> list = session.createCriteria(User.class).list();
            return list;
        }
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("truncate table users").executeUpdate();

            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
