package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {

    static final Logger LOGGER = Logger.getLogger(UserDaoHibernateImpl.class.getName());

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.createSQLQuery("create table if not exists users(id bigint auto_increment primary key, name varchar(50), lastName varchar(50), age tinyint)").addEntity(User.class).executeUpdate();
                transaction.commit();
            } catch (RuntimeException rtex) {
                transaction.rollback();
                throw rtex;
            }
        } catch (Exception e) {
            LOGGER.warning("Ошибка в методе UserDaoHibernateImpl.createUsersTable: " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
                transaction.commit();
            } catch (RuntimeException rtex) {
                transaction.rollback();
                throw rtex;
            }
        } catch (Exception e) {
            LOGGER.warning("Ошибка в методе UserDaoHibernateImpl.dropUsersTable: " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                User user = new User(name,lastName,age);
                session.save(user);
                transaction.commit();
            } catch (RuntimeException rtex) {
                transaction.rollback();
                throw rtex;
            }
        } catch (Exception e) {
            LOGGER.warning("Ошибка в методе UserDaoHibernateImpl.saveUser: " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(session.load(User.class, id));
                transaction.commit();
            } catch (RuntimeException rtex) {
                transaction.rollback();
                throw rtex;
            }
        } catch (Exception e) {
            LOGGER.warning("Ошибка в методе UserDaoHibernateImpl.removeUserById: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> users = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                users = session.createQuery("select u from User u", User.class);
                transaction.commit();
            } catch (RuntimeException rtex) {
                transaction.rollback();
                throw rtex;
            }
        } catch (Exception e) {
            LOGGER.warning("Ошибка в методе UserDaoHibernateImpl.getAllUsers: " + e.getMessage());
        }
        return (List<User>) users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.createQuery("delete from User").executeUpdate();
                transaction.commit();
            } catch (RuntimeException rtex) {
                transaction.rollback();
                throw rtex;
            }
        } catch (Exception e) {
            LOGGER.warning("Ошибка в методе UserDaoHibernateImpl.cleanUsersTable: " + e.getMessage());
        }
    }
}
