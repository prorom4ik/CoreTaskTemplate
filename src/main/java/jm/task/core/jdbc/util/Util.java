package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection connectToDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtest","root","rtorop");
        } catch (SQLException e) {
            System.out.println("Возникла ошибка при подключении к базе данных");
        }
        return connection;
    }
}
