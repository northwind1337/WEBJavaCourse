package dao_layer;

import java.util.List;

import entities.Student;


public interface StudentDaoLayer extends MainDaoLayer<Student, Long> {
    List<Student> getStudents() throws DaoExceptionLayer;

    List<Student> getBySurname(String surname) throws DaoExceptionLayer;

	List<Student> getUnblockedStudents() throws DaoExceptionLayer;

	List<Student> getBlockedStudents() throws DaoExceptionLayer;

	void setBlock(int id) throws DaoExceptionLayer;

	void setUnblock(int id) throws DaoExceptionLayer;

	boolean isBlocked(Long id) throws DaoExceptionLayer;
}
