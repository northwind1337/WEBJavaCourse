package service;

import java.util.List;

import entities.Course;


public interface CourseService {
    List<Course> findAll() throws ServiceException;

    List<Course> findByInstructor(String condition) throws ServiceException;

    Course findById(Long id) throws ServiceException;

    Long save(Course course) throws ServiceException;
    
    List<Course> getOtherByStudent(Long id) throws ServiceException;

	void delete(Long id) throws ServiceException;
}
