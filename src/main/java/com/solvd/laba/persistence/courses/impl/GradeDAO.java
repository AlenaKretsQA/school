package com.solvd.laba.persistence.courses.impl;

import com.solvd.laba.domain.courses.info.Grade;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses.IGradeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.annotations.Param;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO implements IGradeDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String ADD_GRADE_QUERY = "INSERT INTO grades (id, course_id, grade) VALUES (?, ?, ?)";
    private static final String GET_GRADE_BY_ID_QUERY = "SELECT id as grade_id, grade " +
            "FROM grades " +
            "WHERE id = ?";
    private static final String GET_ALL_GRADES_QUERY = "SELECT * FROM grades";
    private static final String UPDATE_GRADE_QUERY = "UPDATE grades SET course_id = ?, grade = ? WHERE id = ?";
    private static final String DELETE_GRADE_QUERY = "DELETE FROM grades WHERE id = ?";

    private static Grade mapRow(ResultSet resultSet) throws SQLException {
        Grade grade = new Grade();
        grade.setId(resultSet.getLong("id"));
        grade.setCourseId(resultSet.getLong("course_id"));
        grade.setGrade(resultSet.getString("grade"));
        return grade;
    }

    @Override
    public Grade getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_GRADE_BY_ID_QUERY)) {
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
    public void create(@Param("grade") Grade grade, @Param("gradeId") Long gradeId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_GRADE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, gradeId);
            preparedStatement.setLong(2, grade.getCourseId());
            preparedStatement.setString(3, grade.getGrade());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                grade.setId(generatedId);
                LOGGER.info("Grade created with id: {}", generatedId);
            } else {
                LOGGER.warn("Failed to retrieve generated id for Grade");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Grade> getAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Grade> grades = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_GRADES_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                grades.add(mapRow(result));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return grades;
    }

    @Override
    public void update(Grade grade) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GRADE_QUERY)) {
            preparedStatement.setLong(1, grade.getCourseId());
            preparedStatement.setString(2, grade.getGrade());
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
    public void delete(long id) {
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
}
