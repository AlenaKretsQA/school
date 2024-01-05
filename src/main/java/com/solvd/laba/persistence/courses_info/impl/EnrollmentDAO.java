package com.solvd.laba.persistence.courses_info.impl;

import com.solvd.laba.domain.courses_info.Enrollment;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses_info.IEnrollmentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO implements IEnrollmentDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String ADD_ENROLLMENT_QUERY = "INSERT INTO enrollments (id, enrollment_date, student_id, course_id) VALUES (?, ?, ?, ?)";
    private static final String GET_ENROLLMENT_BY_ID_QUERY = "SELECT * FROM enrollments WHERE id = ?";
    private static final String GET_ALL_ENROLLMENTS_QUERY = "SELECT * FROM enrollments";
    private static final String UPDATE_ENROLLMENT_QUERY = "UPDATE enrollments SET enrollment_date = ?, student_id = ?, course_id = ? WHERE id = ?";
    private static final String DELETE_ENROLLMENT_QUERY = "DELETE FROM enrollments WHERE id = ?";


    @Override
    public void addEnrollment(Enrollment enrollment) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ENROLLMENT_QUERY)) {
            preparedStatement.setLong(1, enrollment.getId());
            preparedStatement.setTimestamp(2, (Timestamp) enrollment.getEnrollmentDate());
            preparedStatement.setLong(3, enrollment.getId());
            preparedStatement.setLong(4, enrollment.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Enrollment added: {}", enrollment);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    private static Enrollment mapRow(ResultSet resultSet) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(resultSet.getLong("id"));
        enrollment.setEnrollmentDate(resultSet.getTimestamp("enrollment_date"));
        /*enrollment.setStudentId(resultSet.getLong("student_id"));
        enrollment.setCourseId(resultSet.getLong("course_id"));*/
        return enrollment;
    }

    @Override
    public Enrollment getEnrollmentById(long id) {
        return null;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {

        Connection connection = CONNECTION_POOL.getConnection();
        List<Enrollment> enrollments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ENROLLMENTS_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                enrollments.add(mapRow(result));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return enrollments;
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ENROLLMENT_QUERY)) {
            preparedStatement.setLong(1, enrollment.getId());
            preparedStatement.setTimestamp(2, (Timestamp) enrollment.getEnrollmentDate());

            preparedStatement.executeUpdate();

            LOGGER.info("Enrollment updated: {}", enrollment);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteEnrollment(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ENROLLMENT_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Enrollment deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }



    @Override
    public Enrollment getById(Long id) {
        return null;
    }

    @Override
    public void update(Enrollment object) {

    }
}
