package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    // Используем экземпляр класса UserDaoHibernateImpl
    private final UserDao userDaoHibernate = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        userDaoHibernate.createUsersTable(); // Вызываем метод через объект
    }

    @Override
    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable(); // Вызываем метод через объект
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age); // Вызываем метод через объект
    }

    @Override
    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id); // Вызываем метод через объект
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoHibernate.getAllUsers(); // Вызываем метод через объект
    }

    @Override
    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable(); // Вызываем метод через объект
    }
}