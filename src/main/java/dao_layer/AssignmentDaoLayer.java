package dao_layer;

import java.sql.ResultSet;
import java.util.List;

import entities.Assignment;
import entities.Student;


public interface AssignmentDaoLayer extends MainDaoLayer<Assignment, Long> {
    List<Assignment> getAssignments() throws DaoExceptionLayer;

    List<Assignment> getAssignmentsByStudent(Long studentId) throws DaoExceptionLayer;

    List<Assignment> getAssignmentsByCourse(Long courseId) throws DaoExceptionLayer;

    Assignment getAssignmentFromResultSet(ResultSet resultSet) throws DaoExceptionLayer;

	int getCountByStudentAndCourse(Long studentId, Long courseId);

	List<Student> getStudentsByCourse(Long courseId);

	Assignment getByStudentAndCourse(Long studentId, Long courseId) throws DaoExceptionLayer;
}
