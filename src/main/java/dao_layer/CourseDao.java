package dao_layer;

import domain.Course;

import java.util.List;


public interface CourseDao extends Dao<Course, Long> {
    List<Course> getCourses() throws DaoException;

    List<Course> getInstructorCourses(Long id) throws DaoException;

	List<Course> getOtherCoursesById(Long id) throws DaoException;

	void delete(Long id) throws DaoException;

}
