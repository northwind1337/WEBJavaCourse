package service.implementation;

import dao.sql.CourseDaoImpl;
import dao.sql.InstructorDaoImpl;
import dao_layer.CourseDao;
import dao_layer.DaoException;
import dao_layer.InstructorDao;
import domain.Course;
import domain.Instructor;
import service.CourseService;
import service.ServiceException;

import java.util.ArrayList;
import java.util.List;


public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao = new CourseDaoImpl();
    private InstructorDao instructorDao = new InstructorDaoImpl();

    @Override
    public List<Course> findAll() throws ServiceException {
        try {
            List<Course> courseList = courseDao.getCourses();
            for (Course course : courseList) {
                course.setInstructor(instructorDao.read(course.getInstructor().getId()));
            }
            return courseList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Course> findByInstructor(String condition) throws ServiceException {
        List<Course> courseListByInstructor = new ArrayList<>();
        try {
            if (condition.matches("\\d++")) {
                Long id = Long.parseLong(condition);
                courseListByInstructor = courseDao.getInstructorCourses(id);
                for (Course course : courseListByInstructor) {
                    course.setInstructor(instructorDao.read(id));
                }
            } else {
                List<Instructor> instructorList = instructorDao.getInstructors();
                for (Instructor instructor : instructorList) {
                    if (instructor.getSurname().equals(condition)) {
                        courseListByInstructor.addAll(courseDao.getInstructorCourses(instructor.getId()));
                        courseListByInstructor.forEach(course -> course.setInstructor(instructor));
                    }
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return courseListByInstructor;
    }

    @Override
    public List<Course> getOtherByStudent(Long studentId) throws ServiceException {
    	List<Course> courses;
        try {
        	courses = courseDao.getOtherCoursesById(studentId);
            for (Course course : courses) {
                course.setInstructor(instructorDao.read(course.getInstructor().getId()));
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return courses;
    }
    
    @Override
    public Course findById(Long id) throws ServiceException {
        try {
            Course course = courseDao.read(id);
            if (course != null) {
                course.setInstructor(instructorDao.read(course.getInstructor().getId()));
            }
            return course;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Course course) throws ServiceException {
        Long id = course.getId();
        try {
            if (id != null) {
                courseDao.update(course);
            } else {
                id = courseDao.create(course);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            courseDao.delete(id, "courses");
            
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
