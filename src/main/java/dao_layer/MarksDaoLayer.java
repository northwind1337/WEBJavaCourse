package dao_layer;

import java.util.List;

import entities.Marks;


public interface MarksDaoLayer extends MainDaoLayer<Marks, Long> {
    List<Marks> getResults() throws DaoExceptionLayer;
    List<Marks> getListByGrade(int fromGrade, int toGrade) throws DaoExceptionLayer;
    List<Marks> getListByAssignment(Long id) throws DaoExceptionLayer;
	Marks findByAssignment(Long id) throws DaoExceptionLayer;
}
