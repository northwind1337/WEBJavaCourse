package service;

import java.util.List;

import entities.Marks;


public interface ResultService {
    List<Marks> findAll() throws ServiceException;

    List<Marks> findByGrade(int from, int to) throws ServiceException;

    List<Marks> findByStudent(Long id) throws ServiceException;

    Marks findById(Long id) throws ServiceException;

    Long save(Marks result) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;

    void fillResult(Marks result) throws ServiceException;

	Marks findByAssignmentId(Long id) throws ServiceException;
}
