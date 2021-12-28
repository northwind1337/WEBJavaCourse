package service;

import domain.Course;

import java.util.List;


public interface CourseService {
    List<Course> findAll() throws ServiceException;

    List<Course> findByInstructor(String condition) throws ServiceException;

    Course findById(Long id) throws ServiceException;

    Long save(Course course) throws ServiceException;
    
    List<Course> getOtherByStudent(Long id) throws ServiceException;

	void delete(Long id) throws ServiceException;
}
