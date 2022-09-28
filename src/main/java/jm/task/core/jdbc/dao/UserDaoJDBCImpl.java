package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;


public class UserDaoJDBCImpl implements UserDao {

    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        PreparedStatement preparedStatement = null;
        String create = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT )";
        try {
            preparedStatement = connection.prepareStatement(create);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

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

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String save = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String remove = "DELETE FROM users WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(remove);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        String getAll = " Select id, name , lastName , age from users";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {

        PreparedStatement preparedStatement = null;
        String clean = "TRUNCATE TABLE  users ";
        try {
            preparedStatement = connection.prepareStatement(clean);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
