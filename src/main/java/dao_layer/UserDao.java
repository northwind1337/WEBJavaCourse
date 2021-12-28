package dao_layer;

import domain.User;

import java.util.List;


public interface UserDao extends Dao<User, Long> {
    List<User> getUsers() throws DaoException;

    User getByLoginAndPass(String login, String password) throws DaoException;

    User getByLogin(String login) throws DaoException;
}
