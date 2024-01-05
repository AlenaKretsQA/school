package com.solvd.laba.persistence.people.impl;

import com.solvd.laba.domain.people.Parent;
import com.solvd.laba.domain.people.Student;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.people.IParentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParentDAO implements IParentDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO parents (id, first_name, last_name) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE parents SET first_name = ?, last_name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM parents WHERE id = ?";
    private static final String GET_STUDENTS_BY_PARENT_ID_QUERY = "SELECT * FROM students WHERE parent_id = ?";

    @Override
    public void create(Parent parent) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setLong(1, parent.getId());
            preparedStatement.setString(2, parent.getFirstName());
            preparedStatement.setString(3, parent.getLastName());

            preparedStatement.executeUpdate();

            LOGGER.info("Parent created: {}", parent);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Parent parent) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, parent.getFirstName());
            preparedStatement.setString(2, parent.getLastName());
            preparedStatement.setLong(3, parent.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Parent updated: {}", parent);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Parent deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Set<Student> getStudentsByParentId(long parentId) {
        Connection connection = CONNECTION_POOL.getConnection();
        Set<Student> students = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENTS_BY_PARENT_ID_QUERY)) {
            preparedStatement.setLong(1, parentId);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                students.add(mapRow(result));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return students;
    }

    private Student mapRow(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        return student;
    }
    @Override
    public List<Parent> getAll() {
        return null;
    }
}
