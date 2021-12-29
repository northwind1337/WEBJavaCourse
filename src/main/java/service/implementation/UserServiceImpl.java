package service.implementation;

import service.ServiceException;
import service.UserService;

import java.util.List;

import dao.sql.UserDao;
import dao_layer.DaoExceptionLayer;
import dao_layer.UserDaoLayer;
import entities.User;


public class UserServiceImpl implements UserService {
    private UserDaoLayer userDao = new UserDao();

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.getUsers();
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(User user) throws ServiceException {
        Long id = user.getId();
        try {
            if (id != null) {
                userDao.update(user);
            } else {
                id = userDao.create(user);
            }
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for (Long id : ids) {
                userDao.delete(id, "users");
            }
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLoginAndPass(String login, String password) throws ServiceException {
        try {
            return userDao.getByLoginAndPass(login, password);
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        try {
            return userDao.getByLogin(login);
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }
}
