package dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao_layer.DaoException;
import db_connection.ConnectionManager;


public class BaseDaoImpl {
	public Connection getConnection() {
		try {
			return ConnectionManager.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void returnConnection(Connection connection) {
		ConnectionManager.releaseConnection(connection);
	}
	
    public void delete(Long id, String tableName) throws DaoException {
        String sql = String.format("DELETE FROM %s WHERE id = ?", tableName);
        PreparedStatement statement = null;
        try {
            statement = ConnectionManager.getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }
}
