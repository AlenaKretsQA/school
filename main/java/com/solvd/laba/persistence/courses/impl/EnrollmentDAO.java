package com.solvd.laba.persistence.courses.impl;

import com.solvd.laba.domain.courses.info.Course;
import com.solvd.laba.domain.courses.info.Enrollment;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses.IEnrollmentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.annotations.Param;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EnrollmentDAO implements IEnrollmentDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String ADD_ENROLLMENT_QUERY = "INSERT INTO enrollments (id, enrollment_date, student_id, course_id) VALUES (?, ?, ?, ?)";
    private static final String GET_ENROLLMENT_BY_ID_QUERY = "SELECT " +
            "e.id AS enrollment_id, " +
            "e.enrollment_date, " +
            "FROM enrollments e " +
            "WHERE e.id = ?";
    private static final String GET_ALL_ENROLLMENTS_BY_STUDENT_ID_QUERY =
            "SELECT e.id AS enrollment_id, e.enrollment_date, " +
                    "s.id AS student_id, s.first_name AS student_first_name, s.last_name AS student_last_name, s.birthdate AS student_birthdate, " +
                    "FROM enrollments e " +
                    "JOIN students s ON e.student_id = s.id " +
                    "WHERE (s.id) = ?";
    private static final String UPDATE_ENROLLMENT_QUERY = "UPDATE enrollments SET enrollment_date = ?, student_id = ?, course_id = ? WHERE id = ?";
    private static final String DELETE_ENROLLMENT_QUERY = "DELETE FROM enrollments WHERE id = ?";


     public static List<Enrollment> mapRow(ResultSet resultSet, List<Enrollment> enrollments) throws SQLException {
         if (enrollments == null) {
             enrollments = new ArrayList<>();
         }

         Long id = resultSet.getLong("enrollment_id");

         if (id != 0) {
             if (!enrollments.stream().anyMatch(enrollment -> enrollment.getId() == id)) {

                 Enrollment enrollment = findById(id, enrollments);
                 enrollment.setEnrollmentDate(resultSet.getDate("enrollment_date"));


                 enrollment.setStudentId(resultSet.getLong("student_id"));
                 enrollment.setCourseId(resultSet.getLong("course_id"));

                 enrollments.add(enrollment);
             }
         }

         return enrollments;
     }

    private static Enrollment findById(Long id, List<Enrollment> enrollments) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Enrollment newEnrollment = new Enrollment();
                    newEnrollment.setId(id);
                    enrollments.add(newEnrollment);
                    return newEnrollment;
                });
    }



    @Override
    public void create(@Param("enrollment") Enrollment enrollment, @Param("enrollmentId") Long enrollmentId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ENROLLMENT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, enrollmentId);
            preparedStatement.setTimestamp(2, (Timestamp) enrollment.getEnrollmentDate());
            preparedStatement.setLong(3, enrollment.getStudentId());
            preparedStatement.setLong(4, enrollment.getCourseId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                enrollment.setId(generatedId);
                LOGGER.info("Enrollment created with id: {}", generatedId);
            } else {
                LOGGER.warn("Failed to retrieve generated id for Enrollment");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Enrollment> getAllEnrollmentsByStudentId(Long studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ENROLLMENTS_BY_STUDENT_ID_QUERY)) {
            preparedStatement.setLong(1, studentId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Enrollment enrollment = mapEnrollment(result);
                enrollments.add(enrollment);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return enrollments;
    }

    @Override
    public void update(Enrollment enrollment) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ENROLLMENT_QUERY)) {
            preparedStatement.setTimestamp(1, (Timestamp) enrollment.getEnrollmentDate());
            preparedStatement.setLong(2, enrollment.getStudentId());
            preparedStatement.setLong(3, enrollment.getCourseId());
            preparedStatement.setLong(4, enrollment.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Enrollment updated: {}", enrollment);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(long id) {
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
        Connection connection = CONNECTION_POOL.getConnection();
        Enrollment enrollment = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ENROLLMENT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                enrollment = mapEnrollments(result);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return enrollment;
    }
    private Enrollment mapEnrollment(ResultSet resultSet) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(resultSet.getLong("id"));
        enrollment.setEnrollmentDate(resultSet.getDate("date"));

        // Assuming these methods exist in their respective DAO classes
        long studentId = resultSet.getLong("student_id");
        if (!resultSet.wasNull()) {
            enrollment.setStudentId(studentId);
        } else {
            enrollment.setStudentId(null);
        }

        long courseId = resultSet.getLong("course_id");
        if (!resultSet.wasNull()) {
            enrollment.setCourseId(courseId);
        } else {
            enrollment.setCourseId(null);
        }

        return enrollment;
    }

}
