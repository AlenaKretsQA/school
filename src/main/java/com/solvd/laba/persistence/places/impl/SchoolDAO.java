package com.solvd.laba.persistence.places.impl;

import com.solvd.laba.domain.places.School;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.places.ISchoolDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAO implements ISchoolDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String GET_BY_ID_QUERY = "SELECT * FROM schools WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM schools";
    private static final String CREATE_QUERY = "INSERT INTO schools (school_name, location, established_date) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE schools SET school_name = ?, location = ?, established_date = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM schools WHERE id = ?";



    @Override
    public void addSchool(School school) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, school.getSchoolName());
            preparedStatement.setString(2, school.getLocation());
            preparedStatement.setTimestamp(3, new Timestamp(school.getEstablishedDate().getTimestamp().getTime()));

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    school.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating school failed, no ID obtained.");
                }
            }

            LOGGER.info("School created: {}", school);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public School getSchoolById(long id) {
        return null;
    }

    private static School mapRow(ResultSet resultSet) throws SQLException {
        School school = new School();
        school.setId(resultSet.getLong("id"));
        school.setSchoolName(resultSet.getString("school_name"));
        school.setLocation(resultSet.getString("location"));
        // school.setEstablishedDate(resultSet.getTimestamp("establish_date"));
        return school;
    }

    @Override
    public List<School> getAllSchools() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<School> schools = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                schools.add(mapRow(result));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return schools;
    }

    @Override
    public void updateSchool(School school) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, school.getSchoolName());
            preparedStatement.setString(2, school.getLocation());
            preparedStatement.setTimestamp(3, new Timestamp(school.getEstablishedDate().getTimestamp().getTime()));
            preparedStatement.setLong(4, school.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("School updated: {}", school);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void deleteSchool(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("School deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public School getById(Long id) {
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
    public void update(School object) {

    }
}
