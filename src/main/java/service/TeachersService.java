package service;

import java.util.List;

import entities.Teacher;


public interface TeachersService {
    List<Teacher> findAll() throws ServiceException;

    Teacher findById(Long id) throws ServiceException;

    Long create(Teacher instructor) throws ServiceException;

    void update(Teacher instructor) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;

    List<Teacher> findBySurname(String surname) throws ServiceException;
}
