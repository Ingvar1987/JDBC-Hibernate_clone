package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Максим", "Петров", (byte) 20);
        userService.saveUser("Иван", "Сидоров", (byte) 21);
        userService.saveUser("Андрей", "Иванов", (byte) 22);
        userService.saveUser("Александр", "Петухов", (byte) 23);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}