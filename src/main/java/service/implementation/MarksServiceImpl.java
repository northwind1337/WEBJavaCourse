package service.implementation;

import service.ResultService;
import service.ServiceException;

import java.util.ArrayList;
import java.util.List;

import dao.sql.AssignmentDao;
import dao.sql.CourseDaoImpl;
import dao.sql.MarksDao;
import dao.sql.StudentDao;
import dao_layer.*;
import entities.Assignment;
import entities.Marks;


public class MarksServiceImpl implements ResultService {
    private MarksDaoLayer resultDao = new MarksDao();
    private AssignmentDaoLayer assignmentDao = new AssignmentDao();
    private StudentDaoLayer studentDao = new StudentDao();
    private CourseDaoLayer courseDao = new CourseDaoImpl();

    public void setResultDao(MarksDaoLayer resultDao) {
        this.resultDao = resultDao;
    }

    public void setAssignmentDao(AssignmentDaoLayer assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public void setStudentDao(StudentDaoLayer studentDao) {
        this.studentDao = studentDao;
    }

    public void setCourseDao(CourseDaoLayer courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Marks> findAll() throws ServiceException {
        try {
            List<Marks> resultList = resultDao.getResults();
            for (Marks result : resultList) {
                fillResult(result);
            }
            return resultList;
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Marks> findByGrade(int from, int to) throws ServiceException {
        try {
            List<Marks> resultListByGrade = resultDao.getListByGrade(from, to);
            for (Marks result : resultListByGrade) {
                fillResult(result);
            }
            return resultListByGrade;
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Marks> findByStudent(Long id) throws ServiceException {
        try {
            List<Assignment> studentAssignment = assignmentDao.getAssignmentsByStudent(id);
            List<Marks> resultList = new ArrayList<>();
            for (Assignment assignment : studentAssignment) {
                resultList.addAll(resultDao.getListByAssignment(assignment.getId()));
            }
            for (Marks result : resultList) {
                for (Assignment assignment : studentAssignment) {
                    if (Math.toIntExact(result.getAssignment().getId()) == assignment.getId()) {
                        result.setAssignment(assignment);
                    }
                }
                result.getAssignment().setCourse(courseDao.read(result.getAssignment().getCourse().getId()));
                result.getAssignment().setStudent(studentDao.read(result.getAssignment().getStudent().getId()));
            }
            return resultList;
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }
    
    @Override
    public Marks findByAssignmentId(Long id) throws ServiceException {
        try {
            Marks result = resultDao.findByAssignment(id);
            fillResult(result);
            return result;
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Marks findById(Long id) throws ServiceException {
        try {
            Marks result = resultDao.read(id);
            fillResult(result);
            return result;
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void fillResult(Marks result) throws ServiceException {
    	if (result == null) {
    		return;
    	}
    	try {
            result.setAssignment(assignmentDao.read(result.getAssignment().getId()));
            result.getAssignment().setCourse(courseDao.read(result.getAssignment().getCourse().getId()));
            result.getAssignment().setStudent(studentDao.read(result.getAssignment().getStudent().getId()));
        } catch (DaoExceptionLayer e) {
            e.printStackTrace();
        }

    }

    @Override
    public Long save(Marks result) throws ServiceException {
        Long id = result.getId();
        try {
            if (id != null) {
                resultDao.update(result);
            } else {
                id = resultDao.create(result);
            }
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for (Long id : ids) {
                resultDao.delete(id, "results");
            }
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }
}
