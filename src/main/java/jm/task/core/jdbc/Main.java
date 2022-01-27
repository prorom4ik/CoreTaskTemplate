package jm.task.core.jdbc;

import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Roman", "Torop", (byte) 19);
        userService.saveUser("Kirill", "Kireev", (byte) 20);
        userService.saveUser("Anton", "Kryvodskiy", (byte) 24);
        userService.saveUser("Pavel", "Morozov", (byte) 32);

        List<User> users= userService.getAllUsers();
        for (User user : users)
            System.out.println(user);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
