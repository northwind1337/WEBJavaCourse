package dao_layer;

import domain.Result;

import java.util.List;


public interface ResultDao extends Dao<Result, Long> {
    List<Result> getResults() throws DaoException;
    List<Result> getListByGrade(int fromGrade, int toGrade) throws DaoException;
    List<Result> getListByAssignment(Long id) throws DaoException;
	Result findByAssignment(Long id) throws DaoException;
}
