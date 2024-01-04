package com.solvd.laba.persistence.people.impl;
import com.solvd.laba.domain.people.Student;
import com.solvd.laba.domain.people.Teacher;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.people.ITeacherDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO implements ITeacherDAO {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO teachers (id, first_name, last_name, hire_date) VALUES (?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM teachers WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM teachers";
    private static final String UPDATE_QUERY = "UPDATE teachers SET first_name = ?, last_name = ?, hire_date = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM teachers WHERE id = ?";

    @Override
    public void addTeacher(Teacher teacher) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setLong(1, teacher.getId());
            preparedStatement.setString(2, teacher.getFirstName());
            preparedStatement.setString(3, teacher.getLastName());
            preparedStatement.setDate(4, Date.valueOf(teacher.getHireDate().toLocalDate()));

            preparedStatement.executeUpdate();

            LOGGER.info("Teacher added: {}", teacher);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteTeacher(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Teacher deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return null;
    }
}


