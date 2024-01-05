package com.solvd.laba.persistence.courses_info.impl;

import com.solvd.laba.domain.courses_info.Attendance;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses_info.IAttendanceDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO implements IAttendanceDAO{
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String GET_BY_ID_QUERY = "SELECT * FROM attendance WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM attendance";
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
    public void update(Attendance object) {

    }
    @Override
    public void create(Attendance attendance, long attendanceId) {
        /*Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY){
            preparedStatement.setLong(1, attendanceId);
            preparedStatement.setTimestamp(2, new Timestamp(attendance.getDate().getTime()));
            preparedStatement.setString(3, attendance.getStatus());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }*/
    }

    @Override
    public void update(Attendance attendance, long attendanceId) {

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
