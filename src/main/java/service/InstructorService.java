package service;

import domain.Instructor;

import java.util.List;


public interface InstructorService {
    List<Instructor> findAll() throws ServiceException;

    Instructor findById(Long id) throws ServiceException;

    Long create(Instructor instructor) throws ServiceException;

    void update(Instructor instructor) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;

    List<Instructor> findBySurname(String surname) throws ServiceException;
}
