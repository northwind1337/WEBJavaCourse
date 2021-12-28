package service;

import domain.Result;

import java.util.List;


public interface ResultService {
    List<Result> findAll() throws ServiceException;

    List<Result> findByGrade(int from, int to) throws ServiceException;

    List<Result> findByStudent(Long id) throws ServiceException;

    Result findById(Long id) throws ServiceException;

    Long save(Result result) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;

    void fillResult(Result result) throws ServiceException;

	Result findByAssignmentId(Long id) throws ServiceException;
}
