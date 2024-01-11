package com.solvd.laba.persistence.courses.impl;

import com.solvd.laba.domain.courses.info.Grade;
import com.solvd.laba.domain.courses.info.OnlineCourse;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses.IOnlineCourseDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.annotations.Param;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OnlineCourseDAO implements IOnlineCourseDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String ADD_QUERY = "INSERT INTO online_courses (id, url, grade_id) VALUES (?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT id as online_course_id, url, grade_id " +
                    "FROM online_courses " +
                    "WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT id as online_course_id, url,grade_id  " +
                    "FROM online_courses";
    private static final String UPDATE_QUERY = "UPDATE online_courses SET url = ?, grade_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM online_courses WHERE id = ?";

    public static List<OnlineCourse> mapRow(ResultSet resultSet, List<OnlineCourse> onlineCourses) throws SQLException {
        Long onlineCourseId = resultSet.getLong("online_course_id");

        if (onlineCourses == null) {
            onlineCourses = new ArrayList<>();
        }

        if (onlineCourseId != 0) {
            OnlineCourse onlineCourse = findById(onlineCourseId, onlineCourses);

            onlineCourse.setId(onlineCourseId);
            onlineCourse.setUrl(resultSet.getString("online_course_url"));
            onlineCourse.setGradeId((Grade) resultSet.getObject("grade_id"));

            onlineCourses.add(onlineCourse);
        }

        return onlineCourses;
    }
    private OnlineCourse mapOnlineCourse(ResultSet resultSet) throws SQLException {
        OnlineCourse onlineCourse = new OnlineCourse();
        onlineCourse.setId(resultSet.getLong("online_course_id"));
        onlineCourse.setUrl(resultSet.getString("online_course_url"));
       onlineCourse.setGradeId((Grade) resultSet.getObject("grade_id"));
        return onlineCourse;
    }
    private static OnlineCourse findById(Long id, List<OnlineCourse> onlineCourses) {
        return onlineCourses.stream()
                .filter(onlineCourse -> onlineCourse.getId() == id)
                .findFirst()
                .orElseGet(() -> {
                    OnlineCourse newOnlineCourse = new OnlineCourse();
                    newOnlineCourse.setId(id);
                    onlineCourses.add(newOnlineCourse);
                    return newOnlineCourse;
                });
    }

    @Override
    public OnlineCourse getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        OnlineCourse onlineCourse = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                onlineCourse = mapOnlineCourse(result);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return onlineCourse;
    }


        @Override
        public void create(OnlineCourse onlineCourse, Long onlineCourseId) {

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, onlineCourseId);
            preparedStatement.setString(2, onlineCourse.getUrl());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                onlineCourse.setId(generatedId);
                LOGGER.info("OnlineCourse created with id: {}", generatedId);
            } else {
                LOGGER.warn("Failed to retrieve generated id for OnlineCourse");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<OnlineCourse> getAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<OnlineCourse> onlineCourses = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                OnlineCourse onlineCourse = mapOnlineCourse(result);
                onlineCourses.add(onlineCourse);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return onlineCourses;
    }

    @Override
    public void update(OnlineCourse onlineCourse) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, onlineCourse.getUrl());
           //preparedStatement.setLong(2, onlineCourse.getGradeId());


            preparedStatement.executeUpdate();

            LOGGER.info("OnlineCourse updated: {}", onlineCourse);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
        @Override
        public void delete (long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("OnlineCourse deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    }
