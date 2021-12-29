package service;

import java.util.List;

import entities.User;


public interface UserService {
    List<User> findAll() throws ServiceException;

    User findById(Long id) throws ServiceException;

    Long save(User user) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;

    User findByLoginAndPass(String login, String password) throws ServiceException;

    User findByLogin(String login) throws ServiceException;
}
