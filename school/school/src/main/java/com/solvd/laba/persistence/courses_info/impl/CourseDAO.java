package com.solvd.laba.persistence.courses_info.impl;

import com.solvd.laba.domain.courses_info.Course;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses_info.ICourseDAO;
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
    private static final String ADD_COURSE_QUERY = "INSERT INTO courses (id, course_name, credit) VALUES (?, ?, ?)";
    private static final String UPDATE_COURSE_QUERY = "UPDATE courses SET course_name = ?, credit = ? WHERE id = ?";
    private static final String DELETE_COURSE_QUERY = "DELETE FROM courses WHERE id = ?";
    private static final String GET_COURSE_BY_ID_QUERY = "SELECT * FROM courses WHERE id = ?";
    private static final String GET_ALL_COURSES_QUERY = "SELECT * FROM courses";


    @Override
    public void addCourse(Course course) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_COURSE_QUERY)) {
            preparedStatement.setLong(1, course.getId());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setDouble(3, course.getCredit());

            preparedStatement.executeUpdate();

            LOGGER.info("Course added: {}", course);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void updateCourse(Course course) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE_QUERY)) {
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setDouble(2, course.getCredit());
            preparedStatement.setLong(3, course.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Course updated: {}", course);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteCourseById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Course deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Course getCourseById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Course course = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_COURSE_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                course = mapRow(result);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Course> courses = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_COURSES_QUERY)) {
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

    private Course mapRow(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getLong("id"));
        course.setCourseName(resultSet.getString("course_name"));
        course.setCredit(resultSet.getDouble("credit"));
        return course;
    }


    @Override
    public Course getById(Long id) {
        return null;
    }

    @Override
    public void update(Course object) {

    }
}
