package com.solvd.laba.persistence.courses.impl;

import com.solvd.laba.domain.courses.info.Course;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses.ICourseDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO implements ICourseDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String GET_BY_ID_QUERY = "SELECT c.id AS course_id, c.course_name AS course_name, c.credits AS course_credit, " +
            "oc.id AS online_course_id, oc.url AS online_course_url, " +
            "osc.id AS onsite_course_id, osc.room_number AS onsite_course_room_number, osc.date AS onsite_course_date " +
            "e.id AS enrollment_id, e.date AS e.enrollment_date,"+
            "FROM courses c " +
            "LEFT JOIN online_courses oc ON c.id = oc.course_id " +
            "LEFT JOIN onsite_courses osc ON c.id = osc.course_id " +
            "LEFT JOIN enrollments_e ON c.id = e.course_id"+
            "WHERE c.id = ?";
    private static final String GET_ALL_QUERY = "SELECT c.id AS course_id, c.course_name AS course_name, c.credits AS course_credit, " +
            "oc.id AS online_course_id, oc.url AS online_course_url, " +
            "osc.id AS onsite_course_id, osc.room_number AS onsite_course_room_number, osc.date AS onsite_course_date " +
            "e.id AS enrollment_id, e.date AS e.enrollment_date,"+
            "FROM courses c " +
            "LEFT JOIN online_courses oc ON c.id = oc.course_id " +
            "LEFT JOIN onsite_courses osc ON c.id = osc.course_id " +
            "LEFT JOIN enrollments_e ON c.id = e.course_id";
    private static final String CREATE_QUERY = "INSERT INTO courses (course_name, credits) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE courses SET course_name = ?, credits = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM courses WHERE id = ?";
    private Course mapRow(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getLong("id"));
        course.setCourseName(resultSet.getString("course_name"));
        course.setCredit(resultSet.getLong("credit"));

        course.setEnrollments(EnrollmentDAO.mapEnrollments(resultSet));
        course.setOnlineCourses(OnlineCourseDAO.mapOnlineCourses(resultSet));
        course.setOnsiteCourses(OnsiteCourseDAO.mapOnsiteCourses(resultSet));
        return course;
    }
    @Override
    public Course getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return mapRow(result);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return null;
    }

    @Override
    public List<Course> getAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Course> courses = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                courses.add(mapRow(result));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return courses;
    }

    @Override
    public void create(Course course) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1,course.getId());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setDouble(3, course.getCredit());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    course.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating course failed, no ID obtained.");
                }
            }

            LOGGER.info("Course created: {}", course);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }


    @Override
    public void update(Course course) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setLong(1, course.getId());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setDouble(3, course.getCredit());


            preparedStatement.executeUpdate();

            LOGGER.info("Course updated: {}", course);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Course deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }


}
