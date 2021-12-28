package dao_layer;

import domain.Student;

import java.util.List;


public interface StudentDao extends Dao<Student, Long> {
    List<Student> getStudents() throws DaoException;

    List<Student> getBySurname(String surname) throws DaoException;

	List<Student> getUnblockedStudents() throws DaoException;

	List<Student> getBlockedStudents() throws DaoException;

	void setBlock(int id) throws DaoException;

	void setUnblock(int id) throws DaoException;

	boolean isBlocked(Long id) throws DaoException;
}
