package service.implementation;

import service.ServiceException;
import service.StudentService;

import java.util.List;

import dao.sql.StudentDao;
import dao_layer.DaoExceptionLayer;
import dao_layer.StudentDaoLayer;
import entities.Student;


public class StudentServiceImpl implements StudentService {
    private StudentDaoLayer studentDao = new StudentDao();

    @Override
    public List<Student> findAll() throws ServiceException {
        try {
            return studentDao.getStudents();
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Student findById(Long id) throws ServiceException {
        try {
            return studentDao.read(id);
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long create(Student student) throws ServiceException {
        try {
            return studentDao.create(student);
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Student student) throws ServiceException {
        try {
            studentDao.update(student);
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for(Long id : ids) {
                studentDao.delete(id, "students");
            }
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Student> findBySurname(String surname) throws ServiceException {
        try {
            return studentDao.getBySurname(surname);
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }
    
    @Override
	public List<Student> getBlockedStudents() throws ServiceException {
		try {
            return studentDao.getBlockedStudents();
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
	}

	@Override
	public List<Student> getUnblockedStudents() throws ServiceException {
		try {
            return studentDao.getUnblockedStudents();
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
	}

	@Override
	public void setBlock(int studentId) throws ServiceException {
		try {
			studentDao.setBlock(studentId);
		} catch (DaoExceptionLayer e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void setUnblock(int studentId) throws ServiceException {
		try {
			studentDao.setUnblock(studentId);
		} catch (DaoExceptionLayer e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean isBlocked(Long id) throws ServiceException {
		try {
			return studentDao.isBlocked(id);
		} catch (DaoExceptionLayer e) {
			e.printStackTrace();
			return true;
		}
	}
}
