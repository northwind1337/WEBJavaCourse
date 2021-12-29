package dao_layer;

import java.util.List;

import entities.Teacher;


public interface TeachersDaoLayer extends MainDaoLayer<Teacher, Long> {
    List<Teacher> getInstructors() throws DaoExceptionLayer;

    List<Teacher> getBySurname(String surname) throws DaoExceptionLayer;

	Teacher getById(Long id) throws DaoExceptionLayer;

}
