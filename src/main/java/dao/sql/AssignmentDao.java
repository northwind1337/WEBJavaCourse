package dao.sql;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao_layer.AssignmentDaoLayer;
import dao_layer.DaoExceptionLayer;
import entities.Assignment;
import entities.Course;
import entities.Student;


public class AssignmentDao extends GetConnectionDao implements AssignmentDaoLayer {

    @Override
    public List<Assignment> getAssignments() throws DaoExceptionLayer {
        String sql = "SELECT id, student_id, course_id FROM assignments";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<Assignment> assignments = new ArrayList<>();
            while (resultSet.next()) {
                assignments.add(getAssignmentFromResultSet(resultSet));
            }
            return assignments;
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
    public List<Assignment> getAssignmentsByStudent(Long studentId) throws DaoExceptionLayer {
        String sql = "SELECT id, course_id FROM assignments WHERE student_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, studentId);
            resultSet = statement.executeQuery();
            List<Assignment> assignments = new ArrayList<>();
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(resultSet.getLong("id"));
                assignment.setStudent(new Student());
                assignment.getStudent().setId(studentId);
                assignment.setCourse(new Course());
                assignment.getCourse().setId(resultSet.getLong("course_id"));
                
                
                assignments.add(assignment);
            }
            return assignments;
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
    public List<Assignment> getAssignmentsByCourse(Long courseId) throws DaoExceptionLayer {
        String sql = "SELECT id, student_id FROM assignments WHERE course_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, courseId);
            resultSet = statement.executeQuery();
            List<Assignment> assignments = new ArrayList<>();
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(resultSet.getLong("id"));
                assignment.setStudent(new Student());
                assignment.getStudent().setId(resultSet.getLong("student_id"));
                assignment.setCourse(new Course());
                assignment.getCourse().setId(courseId);
                assignments.add(assignment);
            }
            return assignments;
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
    public Long create(Assignment assignment) throws DaoExceptionLayer {
        String sql = "INSERT INTO assignments (student_id, course_id) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, assignment.getStudent().getId());
            statement.setLong(2, assignment.getCourse().getId());
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
    public Assignment read(Long id) throws DaoExceptionLayer {
        String sql = "SELECT student_id, course_id FROM assignments WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Assignment assignment = null;
            if (resultSet.next()) {
                assignment = new Assignment();
                assignment.setId(id);
                assignment.setStudent(new Student());
                assignment.getStudent().setId(resultSet.getLong("student_id"));
                assignment.setCourse(new Course());
                assignment.getCourse().setId(resultSet.getLong("course_id"));
            }
            return assignment;
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
    public void update(Assignment assignment) throws DaoExceptionLayer {
        String sql = "UPDATE assignments SET student_id = ?, course_id = ? WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, assignment.getStudent().getId());
            statement.setLong(2, assignment.getCourse().getId());
            statement.setLong(3, assignment.getId());
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
    public Assignment getAssignmentFromResultSet(ResultSet resultSet) throws DaoExceptionLayer {
        try {
            Assignment assignment = new Assignment();
            assignment.setId(resultSet.getLong("id"));
            assignment.setStudent(new Student());
            assignment.getStudent().setId(resultSet.getLong("student_id"));
            assignment.setCourse(new Course());
            assignment.getCourse().setId(resultSet.getLong("course_id"));
            return assignment;
        } catch (SQLException e) {
            return null;
        }
    }


	@Override
	public int getCountByStudentAndCourse(Long studentId, Long courseId) {
		String sql = "SELECT COUNT(*) from assignments WHERE student_id = ? AND course_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, studentId);
            statement.setLong(2, courseId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
		return count;
	}


	@Override
	public List<Student> getStudentsByCourse(Long courseId) {
		String sql = "SELECT student_id from assignments WHERE course_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        List<Student> students = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, courseId);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
            	Student student = new Student();
            	student.setId(resultSet.getLong(1));
            	students.add(student);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
		return students;
	}


	@Override
	public Assignment getByStudentAndCourse(Long studentId, Long courseId) throws DaoExceptionLayer {
		String sql = "SELECT id FROM assignments WHERE student_id = ? AND course_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, studentId);
            statement.setLong(2, courseId);
            resultSet = statement.executeQuery();
            Assignment assignment = null;
            if (resultSet.next()) {
                assignment = new Assignment();
                assignment.setId(resultSet.getLong(1));
                assignment.setStudent(new Student());
                assignment.getStudent().setId(studentId);
                assignment.setCourse(new Course());
                assignment.getCourse().setId(courseId);
            }
            return assignment;
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