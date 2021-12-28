package service.implementation;

import dao.sql.InstructorDaoImpl;
import dao_layer.DaoException;
import dao_layer.InstructorDao;
import domain.Instructor;
import service.InstructorService;
import service.ServiceException;

import java.util.List;


public class InstructorServiceImpl implements InstructorService {
    private InstructorDao instructorDao = new InstructorDaoImpl();
    
    @Override
    public List<Instructor> findAll() throws ServiceException {
        try {
            return instructorDao.getInstructors();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Instructor findById(Long id) throws ServiceException {
        try {
            return instructorDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long create(Instructor instructor) throws ServiceException {
        try {
            return instructorDao.create(instructor);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Instructor instructor) throws ServiceException {
        try {
            instructorDao.update(instructor);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for(Long id : ids) {
                instructorDao.delete(id, "instructors");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Instructor> findBySurname(String surname) throws ServiceException {
        try {
            return instructorDao.getBySurname(surname);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
