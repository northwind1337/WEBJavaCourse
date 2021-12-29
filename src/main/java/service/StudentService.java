package service;

import java.util.List;

import entities.Student;


public interface StudentService {
    List<Student> findAll() throws ServiceException;

    Student findById(Long id) throws ServiceException;

    Long create(Student student) throws ServiceException;

    void update(Student student) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;

    List<Student> findBySurname(String surname) throws ServiceException;

	List<Student> getUnblockedStudents() throws ServiceException;

	List<Student> getBlockedStudents() throws ServiceException;

	void setBlock(int studentId) throws ServiceException;
	
	void setUnblock(int studentId) throws ServiceException;

	boolean isBlocked(Long id)  throws ServiceException;
}
