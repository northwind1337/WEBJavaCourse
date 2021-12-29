package service.implementation;

import service.AssignmentService;
import service.ServiceException;

import java.util.ArrayList;
import java.util.List;

import dao.sql.AssignmentDao;
import dao.sql.CourseDaoImpl;
import dao.sql.StudentDao;
import dao.sql.TeachersDao;
import dao_layer.AssignmentDaoLayer;
import dao_layer.CourseDaoLayer;
import dao_layer.DaoExceptionLayer;
import dao_layer.StudentDaoLayer;
import dao_layer.TeachersDaoLayer;
import entities.Assignment;
import entities.Course;
import entities.Student;


public class AssignmentServiceImpl implements AssignmentService {
    private AssignmentDaoLayer assignmentDao = new AssignmentDao();
    private StudentDaoLayer studentDao = new StudentDao();
    private CourseDaoLayer courseDao = new CourseDaoImpl();
    private TeachersDaoLayer instructorDao = new TeachersDao();
 
    @Override
    public List<Assignment> findAll() throws ServiceException {
        try {
            List<Assignment> assignmentList = assignmentDao.getAssignments();
            for (Assignment assignment : assignmentList) {
                fillAssignments(assignment);
            }
            return assignmentList;
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Assignment findById(Long id) throws ServiceException {
        try {
            Assignment assignment = assignmentDao.read(id);
            fillAssignments(assignment);
            return assignment;
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Assignment findByStudentAndCourse(Long studentId, Long courseId) throws ServiceException {
        try {
            Assignment assignment = assignmentDao.getByStudentAndCourse(studentId, courseId);
            fillAssignments(assignment);
            return assignment;
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }
    
    @Override
    public List<Assignment> findByStudent(Long studentId) throws ServiceException {
        try {
            List<Assignment> assignmentList = assignmentDao.getAssignmentsByStudent(studentId);
            for (Assignment assignment : assignmentList) {
                fillAssignments(assignment);
            }
            return assignmentList;
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Assignment> findInstructorAssignment(Long instructorId) throws ServiceException {
        try {
            List<Course> instructorCourses = courseDao.getInstructorCourses(instructorId);
            List<Assignment> results = new ArrayList<>();
            for (Course course : instructorCourses) {
                results.addAll(assignmentDao.getAssignmentsByCourse(course.getId()));
            }
            for (Assignment assignment : results) {
                fillAssignments(assignment);
            }
            return results;
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Assignment assignment) throws ServiceException {
        Long id = assignment.getId();
        try {
            if (id != null) {
                assignmentDao.update(assignment);
            } else {
                id = assignmentDao.create(assignment);
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
                assignmentDao.delete(id, "assignments");
            }
        } catch (DaoExceptionLayer e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void fillAssignments(Assignment assignment) throws ServiceException {
        try {
            assignment.setStudent(studentDao.read(assignment.getStudent().getId()));
            assignment.setCourse(courseDao.read(assignment.getCourse().getId()));
            assignment.getCourse().setInstructor(instructorDao.getById(assignment.getCourse().getInstructor().getId()));
        } catch (DaoExceptionLayer e) {
        	e.printStackTrace();
        }
    }

	@Override
	public int checkAssign(Long studentId, Long courseId) {
		return	assignmentDao.getCountByStudentAndCourse(studentId, courseId);
	}

	@Override
	public List<Student> findStudentsByCourse(Long courseId) {
		List<Student> students = assignmentDao.getStudentsByCourse(courseId);
		
		for(int i = 0; i < students.size(); i++) {
			try {
				Student stud = studentDao.read(students.get(i).getId());
				students.set(i, stud);
			} catch (DaoExceptionLayer e) {
				e.printStackTrace();
			}
		}
		return students;
	}
}
