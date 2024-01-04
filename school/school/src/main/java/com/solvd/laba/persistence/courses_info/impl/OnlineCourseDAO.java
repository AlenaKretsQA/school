package com.solvd.laba.persistence.courses_info.impl;

import com.solvd.laba.domain.courses_info.OnlineCourse;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses_info.IOnlineCourseDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OnlineCourseDAO implements IOnlineCourseDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String ADD_ONLINE_COURSE_QUERY = "INSERT INTO online_courses (id, url, course_id) VALUES (?, ?, ?)";
    private static final String GET_ONLINE_COURSE_BY_ID_QUERY = "SELECT * FROM online_courses WHERE id = ?";
    private static final String GET_ALL_ONLINE_COURSES_QUERY = "SELECT * FROM online_courses";
    private static final String UPDATE_ONLINE_COURSE_QUERY = "UPDATE online_courses SET url = ?, course_id = ? WHERE id = ?";
    private static final String DELETE_ONLINE_COURSE_QUERY = "DELETE FROM online_courses WHERE id = ?";

    @Override
    public OnlineCourse getById(Long id) {
        return null;
    }

    @Override
    public void update(OnlineCourse object) {

    }

    @Override
    public void addOnlineCourse(OnlineCourse onlineCourse) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ONLINE_COURSE_QUERY)) {
            preparedStatement.setLong(1, onlineCourse.getId());
            preparedStatement.setString(2, onlineCourse.getUrl());
            //preparedStatement.setLong(3, onlineCourse.getCourseId());

            preparedStatement.executeUpdate();

            LOGGER.info("Online Course added: {}", onlineCourse);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public OnlineCourse getOnlineCourseById(long id) {
        return null;
    }

    @Override
    public List<OnlineCourse> getAllOnlineCourses() {
        return null;
    }

    @Override
    public void updateOnlineCourse(OnlineCourse onlineCourse) {

    }

    @Override
    public void deleteOnlineCourse(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ONLINE_COURSE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Online Course deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
