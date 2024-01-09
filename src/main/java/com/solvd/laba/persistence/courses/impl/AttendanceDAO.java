package com.solvd.laba.persistence.courses.impl;

import com.solvd.laba.domain.courses.info.Attendance;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses.IAttendanceDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO implements IAttendanceDAO{
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String GET_BY_ID_QUERY = "SELECT a.id AS attendance_id, a.date AS attendance_date, a.status AS attendance_status, " +
            "FROM attendance a " +
            "WHERE a.id = ?";
    private static final String GET_ALL_QUERY = "SELECT* FROM attendances";
    private static final String CREATE_QUERY = "INSERT INTO attendance (status, date) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE attendance SET status = ?, date = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM attendance WHERE id = ?";

    private static Attendance mapRow(ResultSet resultSet, List<Attendance> attendances) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setId(resultSet.getLong("id"));
        attendance.setStatus(resultSet.getString("status"));
        return attendance;
    }
    @Override
    public Attendance getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                List<Attendance> attendances = null;
                return mapRow(result, attendances);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return null;
    }
    @Override
    public void update(Attendance attendance, long attendanceId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, attendance.getStatus());
            preparedStatement.setLong(2, attendanceId);

            preparedStatement.executeUpdate();

            LOGGER.info("Attendance updated: {}", attendance);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void update(Attendance object) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, object.getStatus());
            preparedStatement.setLong(2, object.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Attendance updated: {}", object);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }


    @Override
    public void create(Attendance attendance, long attendanceId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, attendanceId);
            preparedStatement.setString(2, attendance.getStatus());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                attendance.setId(generatedId);
                LOGGER.info("Attendance created with id: {}", generatedId);
            } else {
                LOGGER.warn("Failed to retrieve generated id for Attendance");
            }
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

                LOGGER.info("Attendance deleted with id: {}", id);

            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            } finally {
                CONNECTION_POOL.releaseConnection(connection);
            }
    }

    @Override
    public List<Attendance> getAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Attendance> attendances = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                attendances.add(mapRow(result, attendances));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return attendances;
    }
}
