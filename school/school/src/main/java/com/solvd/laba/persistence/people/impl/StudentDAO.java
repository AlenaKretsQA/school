package com.solvd.laba.persistence.people.impl;

import com.solvd.laba.domain.people.Student;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.people.IStudentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class StudentDAO implements IStudentDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO students (id, first_name, last_name, birthdate, department_id) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM students WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM students";
    private static final String UPDATE_QUERY = "UPDATE students SET first_name = ?, last_name = ?, birthdate = ?, department_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM students WHERE id = ?";

    @Override
    public void addStudent(Student student) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setLong(1, student.getId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setDate(4, Date.valueOf(String.valueOf(student.getBirthdate())));

            preparedStatement.executeUpdate();

            LOGGER.info("Student added: {}", student);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void updateStudent(Student student) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setDate(3, Date.valueOf(String.valueOf(student.getBirthdate())));
            preparedStatement.setLong(5, student.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Student updated: {}", student);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteStudent(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Student deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Student getStudentById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Student student = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                student = mapRow(result);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return student;
    }

    @Override
    public Set<Student> getAllStudents() {
        Connection connection = CONNECTION_POOL.getConnection();
        Set<Student> students = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
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
        student.setBirthdate(Date.valueOf(String.valueOf(student.getBirthdate())));
        return student;
    }

}
