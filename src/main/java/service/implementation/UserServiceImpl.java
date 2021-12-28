package service.implementation;

import dao.sql.UserDaoImpl;
import dao_layer.DaoException;
import dao_layer.UserDao;
import domain.User;
import service.ServiceException;
import service.UserService;

import java.util.List;


public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.getUsers();
        } catch (DaoException e) {
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
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for (Long id : ids) {
                userDao.delete(id, "users");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLoginAndPass(String login, String password) throws ServiceException {
        try {
            return userDao.getByLoginAndPass(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        try {
            return userDao.getByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
