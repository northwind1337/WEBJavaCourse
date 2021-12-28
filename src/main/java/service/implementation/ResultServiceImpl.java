package service.implementation;

import dao.sql.AssignmentDaoImpl;
import dao.sql.CourseDaoImpl;
import dao.sql.ResultDaoImpl;
import dao.sql.StudentDaoImpl;
import dao_layer.*;
import domain.Assignment;
import domain.Result;
import service.ResultService;
import service.ServiceException;

import java.util.ArrayList;
import java.util.List;


public class ResultServiceImpl implements ResultService {
    private ResultDao resultDao = new ResultDaoImpl();
    private AssignmentDao assignmentDao = new AssignmentDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();
    private CourseDao courseDao = new CourseDaoImpl();

    public void setResultDao(ResultDao resultDao) {
        this.resultDao = resultDao;
    }

    public void setAssignmentDao(AssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public List<Result> findAll() throws ServiceException {
        try {
            List<Result> resultList = resultDao.getResults();
            for (Result result : resultList) {
                fillResult(result);
            }
            return resultList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Result> findByGrade(int from, int to) throws ServiceException {
        try {
            List<Result> resultListByGrade = resultDao.getListByGrade(from, to);
            for (Result result : resultListByGrade) {
                fillResult(result);
            }
            return resultListByGrade;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Result> findByStudent(Long id) throws ServiceException {
        try {
            List<Assignment> studentAssignment = assignmentDao.getAssignmentsByStudent(id);
            List<Result> resultList = new ArrayList<>();
            for (Assignment assignment : studentAssignment) {
                resultList.addAll(resultDao.getListByAssignment(assignment.getId()));
            }
            for (Result result : resultList) {
                for (Assignment assignment : studentAssignment) {
                    if (Math.toIntExact(result.getAssignment().getId()) == assignment.getId()) {
                        result.setAssignment(assignment);
                    }
                }
                result.getAssignment().setCourse(courseDao.read(result.getAssignment().getCourse().getId()));
                result.getAssignment().setStudent(studentDao.read(result.getAssignment().getStudent().getId()));
            }
            return resultList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    
    @Override
    public Result findByAssignmentId(Long id) throws ServiceException {
        try {
            Result result = resultDao.findByAssignment(id);
            fillResult(result);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Result findById(Long id) throws ServiceException {
        try {
            Result result = resultDao.read(id);
            fillResult(result);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void fillResult(Result result) throws ServiceException {
    	if (result == null) {
    		return;
    	}
    	try {
            result.setAssignment(assignmentDao.read(result.getAssignment().getId()));
            result.getAssignment().setCourse(courseDao.read(result.getAssignment().getCourse().getId()));
            result.getAssignment().setStudent(studentDao.read(result.getAssignment().getStudent().getId()));
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Long save(Result result) throws ServiceException {
        Long id = result.getId();
        try {
            if (id != null) {
                resultDao.update(result);
            } else {
                id = resultDao.create(result);
            }
        } catch (DaoException e) {
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
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
