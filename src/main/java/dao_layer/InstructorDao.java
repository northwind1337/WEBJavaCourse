package dao_layer;

import domain.Instructor;

import java.util.List;


public interface InstructorDao extends Dao<Instructor, Long> {
    List<Instructor> getInstructors() throws DaoException;

    List<Instructor> getBySurname(String surname) throws DaoException;

	Instructor getById(Long id) throws DaoException;

}
