package service.implementation;

import service.TeachersService;
import service.ServiceException;

import java.util.List;

import dao.sql.TeachersDao;
import dao_layer.DaoExceptionLayer;
import dao_layer.TeachersDaoLayer;
import entities.Teacher;


public class TeachersServiceImpl implements TeachersService {
    private TeachersDaoLayer instructorDao = new TeachersDao();
    
    @Override
    public List<Teacher> findAll() throws ServiceException {
        try {
            return instructorDao.getInstructors();
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Teacher findById(Long id) throws ServiceException {
        try {
            return instructorDao.read(id);
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long create(Teacher instructor) throws ServiceException {
        try {
            return instructorDao.create(instructor);
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Teacher instructor) throws ServiceException {
        try {
            instructorDao.update(instructor);
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for(Long id : ids) {
                instructorDao.delete(id, "instructors");
            }
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Teacher> findBySurname(String surname) throws ServiceException {
        try {
            return instructorDao.getBySurname(surname);
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }
}
