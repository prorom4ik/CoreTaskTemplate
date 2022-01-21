package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = Util.connectToDB();
    Statement statement;
    String sql;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS users(" +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "name varchar(50)," +
                    "secondName varchar(50)," +
                    "age int," +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Возникла ошибка при создании таблицы users");
        }
    }

    public void dropUsersTable() {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            sql = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(sql);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Возникла ошибка при очищении таблицы users");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            sql = "INSERT INTO users(name, secondName, age) VALUES('" +
            name + "','" + lastName + "','" + age + "')";
            statement.executeUpdate(sql);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Возникла ошибка при добавлении пользователя");
        }
    }

    public void removeUserById(long id) {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            sql = "DELETE FROM users WHERE id = " + id;
            statement.executeUpdate(sql);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Возникла ошибка при удалении пользователя");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            sql = "SELECT * FROM users";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(Long.parseLong(rs.getString(1)));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(Byte.parseByte(rs.getString(4)));
                users.add(user);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Возникла ошибка при извлечении пользователей");
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            sql = "TRUNCATE TABLE users";
            statement.executeUpdate(sql);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Возникла ошибка при удалении таблицы users");
        }
    }
}
