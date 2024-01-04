package com.solvd.laba.persistence.courses_info.impl;

import com.solvd.laba.domain.courses_info.Grade;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses_info.IGradeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

class GradeDAO implements IGradeDAO {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String ADD_GRADE_QUERY = "INSERT INTO grades (id, enrollment_id, grade) VALUES (?, ?, ?)";
    private static final String GET_GRADE_BY_ID_QUERY = "SELECT * FROM grades WHERE id = ?";
    private static final String GET_ALL_GRADES_QUERY = "SELECT * FROM grades";
    private static final String UPDATE_GRADE_QUERY = "UPDATE grades SET enrollment_id = ?, grade = ? WHERE id = ?";
    private static final String DELETE_GRADE_QUERY = "DELETE FROM grades WHERE id = ?";

    @Override
    public void addGrade(Grade grade) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_GRADE_QUERY)) {
            preparedStatement.setLong(1, grade.getId());
           // preparedStatement.setLong(2, grade.getEnrollmentId());
           // preparedStatement.setString(3, grade.getGrade());

            preparedStatement.executeUpdate();

            LOGGER.info("Grade added: {}", grade);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Grade getGradeById(long id) {
        return null;
    }

    @Override
    public List<Grade> getAllGrades() {
        return null;
    }

    @Override
    public void updateGrade(Grade grade) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GRADE_QUERY)) {
           // preparedStatement.setLong(1, grade.getEnrollmentId());
            //preparedStatement.setString(2, grade.getGrade());
            preparedStatement.setLong(3, grade.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Grade updated: {}", grade);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteGrade(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GRADE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Grade deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Grade getById(Long id) {
        return null;
    }

    @Override
    public void update(Grade object) {

    }
}