package service.implementation;

import dao.sql.AssignmentDaoImpl;
import dao.sql.CourseDaoImpl;
import dao.sql.InstructorDaoImpl;
import dao.sql.StudentDaoImpl;
import dao_layer.AssignmentDao;
import dao_layer.CourseDao;
import dao_layer.DaoException;
import dao_layer.InstructorDao;
import dao_layer.StudentDao;
import domain.Assignment;
import domain.Course;
import domain.Student;
import service.AssignmentService;
import service.ServiceException;

import java.util.ArrayList;
import java.util.List;


public class AssignmentServiceImpl implements AssignmentService {
    private AssignmentDao assignmentDao = new AssignmentDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();
    private CourseDao courseDao = new CourseDaoImpl();
    private InstructorDao instructorDao = new InstructorDaoImpl();
 
    @Override
    public List<Assignment> findAll() throws ServiceException {
        try {
            List<Assignment> assignmentList = assignmentDao.getAssignments();
            for (Assignment assignment : assignmentList) {
                fillAssignments(assignment);
            }
            return assignmentList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Assignment findById(Long id) throws ServiceException {
        try {
            Assignment assignment = assignmentDao.read(id);
            fillAssignments(assignment);
            return assignment;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Assignment findByStudentAndCourse(Long studentId, Long courseId) throws ServiceException {
        try {
            Assignment assignment = assignmentDao.getByStudentAndCourse(studentId, courseId);
            fillAssignments(assignment);
            return assignment;
        } catch (DaoException e) {
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
        } catch (DaoException e) {
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
        } catch (DaoException e) {
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
        } catch (DaoException e) {
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
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void fillAssignments(Assignment assignment) throws ServiceException {
        try {
            assignment.setStudent(studentDao.read(assignment.getStudent().getId()));
            assignment.setCourse(courseDao.read(assignment.getCourse().getId()));
            assignment.getCourse().setInstructor(instructorDao.getById(assignment.getCourse().getInstructor().getId()));
        } catch (DaoException e) {
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
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return students;
	}
}
