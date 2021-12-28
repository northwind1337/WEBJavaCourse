package dao_layer;

import domain.Assignment;
import domain.Student;

import java.sql.ResultSet;
import java.util.List;


public interface AssignmentDao extends Dao<Assignment, Long> {
    List<Assignment> getAssignments() throws DaoException;

    List<Assignment> getAssignmentsByStudent(Long studentId) throws DaoException;

    List<Assignment> getAssignmentsByCourse(Long courseId) throws DaoException;

    Assignment getAssignmentFromResultSet(ResultSet resultSet) throws DaoException;

	int getCountByStudentAndCourse(Long studentId, Long courseId);

	List<Student> getStudentsByCourse(Long courseId);

	Assignment getByStudentAndCourse(Long studentId, Long courseId) throws DaoException;
}
