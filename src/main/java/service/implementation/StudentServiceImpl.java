package service.implementation;

import dao.sql.StudentDaoImpl;
import dao_layer.DaoException;
import dao_layer.StudentDao;
import domain.Student;
import service.ServiceException;
import service.StudentService;

import java.util.List;


public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> findAll() throws ServiceException {
        try {
            return studentDao.getStudents();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Student findById(Long id) throws ServiceException {
        try {
            return studentDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long create(Student student) throws ServiceException {
        try {
            return studentDao.create(student);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Student student) throws ServiceException {
        try {
            studentDao.update(student);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for(Long id : ids) {
                studentDao.delete(id, "students");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Student> findBySurname(String surname) throws ServiceException {
        try {
            return studentDao.getBySurname(surname);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    
    @Override
	public List<Student> getBlockedStudents() throws ServiceException {
		try {
            return studentDao.getBlockedStudents();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
	}

	@Override
	public List<Student> getUnblockedStudents() throws ServiceException {
		try {
            return studentDao.getUnblockedStudents();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
	}

	@Override
	public void setBlock(int studentId) throws ServiceException {
		try {
			studentDao.setBlock(studentId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void setUnblock(int studentId) throws ServiceException {
		try {
			studentDao.setUnblock(studentId);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean isBlocked(Long id) throws ServiceException {
		try {
			return studentDao.isBlocked(id);
		} catch (DaoException e) {
			e.printStackTrace();
			return true;
		}
	}
}
