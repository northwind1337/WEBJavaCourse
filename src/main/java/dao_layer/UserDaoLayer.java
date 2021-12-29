package dao_layer;

import java.util.List;

import entities.User;


public interface UserDaoLayer extends MainDaoLayer<User, Long> {
    List<User> getUsers() throws DaoExceptionLayer;

    User getByLoginAndPass(String login, String password) throws DaoExceptionLayer;

    User getByLogin(String login) throws DaoExceptionLayer;
}
