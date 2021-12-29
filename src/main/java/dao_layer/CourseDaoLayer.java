package dao_layer;

import java.util.List;

import entities.Course;


public interface CourseDaoLayer extends MainDaoLayer<Course, Long> {
    List<Course> getCourses() throws DaoExceptionLayer;

    List<Course> getInstructorCourses(Long id) throws DaoExceptionLayer;

	List<Course> getOtherCoursesById(Long id) throws DaoExceptionLayer;

	void delete(Long id) throws DaoExceptionLayer;

}
