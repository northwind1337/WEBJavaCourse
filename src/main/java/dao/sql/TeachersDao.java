package dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao_layer.DaoExceptionLayer;
import dao_layer.TeachersDaoLayer;
import entities.Teacher;


public class TeachersDao extends GetConnectionDao implements TeachersDaoLayer {

    @Override
    public List<Teacher> getInstructors() throws DaoExceptionLayer {
        String sql = "SELECT id, surname, name FROM teachers";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<Teacher> instructors = new ArrayList<>();
            while (resultSet.next()) {
                Teacher instructor = new Teacher();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
                instructors.add(instructor);
            }
            return instructors;
        } catch (SQLException e) {
            throw new DaoExceptionLayer(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Teacher> getBySurname(String surname) throws DaoExceptionLayer {
        String sql = "SELECT id, surname, name FROM teachers WHERE surname = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, surname);
            resultSet = statement.executeQuery();
            List<Teacher> instructors = new ArrayList<>();
            while (resultSet.next()) {
                Teacher instructor = new Teacher();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
                instructors.add(instructor);
            }
            return instructors;
        } catch (SQLException e) {
            throw new DaoExceptionLayer(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

    @Override
    public Long create(Teacher instructor) throws DaoExceptionLayer {
        String sql = "INSERT INTO teachers (id, surname, name) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, instructor.getId());
            statement.setString(2, instructor.getSurname());
            statement.setString(3, instructor.getName());
            statement.executeUpdate();
            return instructor.getId();
        } catch (SQLException e) {
            throw new DaoExceptionLayer(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

    @Override
    public Teacher read(Long id) throws DaoExceptionLayer {
        String sql = "SELECT id, surname, name FROM teachers WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Teacher instructor = null;
            if (resultSet.next()) {
                instructor = new Teacher();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
            }
            return instructor;
        } catch (SQLException e) {
            throw new DaoExceptionLayer(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            	e.printStackTrace();           
            }
        }
    }

    @Override
    public void update(Teacher instructor) throws DaoExceptionLayer {
        String sql = "UPDATE teachers SET surname = ?, name = ? WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, instructor.getSurname());
            statement.setString(2, instructor.getName());
            statement.setLong(3, instructor.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoExceptionLayer(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }
    
	@Override
	public Teacher getById(Long id) throws DaoExceptionLayer {
		String sql = "SELECT surname, name FROM teachers WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Teacher instructor = new Teacher();
            while (resultSet.next()) {
                instructor.setId(id);
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
            }
            return instructor;
        } catch (SQLException e) {
            throw new DaoExceptionLayer(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
	}
}