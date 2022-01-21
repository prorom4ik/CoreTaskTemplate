package jm.task.core.jdbc;

import java.util.List;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        dao.createUsersTable();

        dao.saveUser("Roman", "Torop", (byte) 19);
        dao.saveUser("Kirill", "Kireev", (byte) 20);
        dao.saveUser("Anton", "Kryvodskiy", (byte) 24);
        dao.saveUser("Pavel", "Morozov", (byte) 32);

        List<User> users= dao.getAllUsers();
        for (User user : users)
            System.out.println(user);

        dao.cleanUsersTable();
        dao.dropUsersTable();
    }
}
