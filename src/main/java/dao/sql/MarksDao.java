package dao.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao_layer.DaoExceptionLayer;
import dao_layer.MarksDaoLayer;
import entities.Assignment;
import entities.Marks;


public class MarksDao extends GetConnectionDao implements MarksDaoLayer {


    @Override
    public List<Marks> getResults() throws DaoExceptionLayer {
        String sql = "SELECT id, assignment_id, grade FROM marks";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<Marks> results = new ArrayList<>();
            while (resultSet.next()) {
                Marks result = new Marks();
                result.setId(resultSet.getLong("id"));
                result.setAssignment(new Assignment());
                result.getAssignment().setId(resultSet.getLong("assignment_id"));
                result.setGrade(resultSet.getInt("grade"));
                results.add(result);
            }
            return results;
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
    public List<Marks> getListByGrade(int fromGrade, int toGrade) throws DaoExceptionLayer {
        String sql = "SELECT id, assignment_id, grade FROM marks WHERE grade BETWEEN ? AND ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, fromGrade);
            statement.setInt(2, toGrade);
            resultSet = statement.executeQuery();
            List<Marks> results = new ArrayList<>();
            while (resultSet.next()) {
                Marks result = new Marks();
                result.setId(resultSet.getLong("id"));
                result.setAssignment(new Assignment());
                result.getAssignment().setId(resultSet.getLong("assignment_id"));
                result.setGrade(resultSet.getInt("grade"));
                results.add(result);
            }
            return results;
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
    public List<Marks> getListByAssignment(Long id) throws DaoExceptionLayer {
        String sql = "SELECT id, grade FROM marks WHERE assignment_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Marks> results = new ArrayList<>();
            while (resultSet.next()) {
                Marks result = new Marks();
                result.setId(resultSet.getLong("id"));
                result.setAssignment(new Assignment());
                result.getAssignment().setId(id);
                result.setGrade(resultSet.getInt("grade"));
                results.add(result);
            }
            return results;
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
    public Long create(Marks result) throws DaoExceptionLayer {
        String sql = "INSERT INTO marks (assignment_id, grade) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, result.getAssignment().getId());
            statement.setInt(2, result.getGrade());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
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
    public Marks read(Long id) throws DaoExceptionLayer {
        String sql = "SELECT assignment_id, grade FROM marks WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Marks result = null;
            if (resultSet.next()) {
                result = new Marks();
                result.setId(id);
                result.setAssignment(new Assignment());
                result.getAssignment().setId(resultSet.getLong("assignment_id"));
                result.setGrade(resultSet.getInt("grade"));
            }
            return result;
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
    public void update(Marks result) throws DaoExceptionLayer {
        String sql = "UPDATE marks SET assignment_id = ?, grade = ? WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, result.getAssignment().getId());
            statement.setInt(2, result.getGrade());
            statement.setLong(3, result.getId());
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
	public Marks findByAssignment(Long id) throws DaoExceptionLayer {
		String sql = "SELECT id, grade FROM marks WHERE assignment_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Marks result = null;
            if (resultSet.next()) {
                result = new Marks();
                result.setId(resultSet.getLong("id"));
                result.setAssignment(new Assignment());
                result.getAssignment().setId(id);
                result.setGrade(resultSet.getInt("grade"));
            }
            return result;
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
