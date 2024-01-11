package com.solvd.laba.persistence.courses.impl;

import com.solvd.laba.domain.courses.info.Attendance;
import com.solvd.laba.domain.courses.info.OnlineCourse;
import com.solvd.laba.domain.courses.info.OnsiteCourse;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses.IOnsiteCourseDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ibatis.annotations.Param;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.solvd.laba.persistence.people.impl.TeacherDAO.findById;

public class OnsiteCourseDAO implements IOnsiteCourseDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String ADD_QUERY = "INSERT INTO onsite_courses (id, room_number, date, course_id) VALUES (?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY =
            "SELECT id as onsite_course_id, room_number, date, course_id " +
                    "FROM onsite_courses " +
                    "WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM onsite_courses";
    private static final String UPDATE_QUERY = "UPDATE onsite_courses SET room_number = ?, date = ?, course_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM onsite_courses WHERE id = ?";

    public static List<OnsiteCourse> mapOnsiteCourses(ResultSet resultSet, List<OnsiteCourse> onsiteCourses) throws SQLException {
        if (onsiteCourses == null) {
            onsiteCourses = new ArrayList<>();
        }

        Long onsiteCourseId = resultSet.getLong("onsite_course_id");

        if (onsiteCourseId != 0) {
            OnsiteCourse onsiteCourse = findById(onsiteCourseId, onsiteCourses);

            onsiteCourse.setId(onsiteCourseId);
            onsiteCourse.setRoomNumber(resultSet.getInt("onsite_course_room_number"));
            //onsiteCourse.setDate(resultSet.getTimestamp("onsite_course_date"));

            List<Attendance> attendances = (List<Attendance>) AttendanceDAO.mapRow(resultSet, onsiteCourse.getAttendances());
            onsiteCourse.setAttendances(attendances);

            onsiteCourses.add(onsiteCourse);
        }

        return onsiteCourses;
    }

    private static OnsiteCourse findById(Long id, List<OnsiteCourse> onsiteCourses) {
        return onsiteCourses.stream()
                .filter(onsiteCourse -> onsiteCourse.getId() == id)
                .findFirst()
                .orElseGet(() -> {
                    OnsiteCourse newOnsiteCourse = new OnsiteCourse();
                    newOnsiteCourse.setId(id);
                    onsiteCourses.add(newOnsiteCourse);
                    return newOnsiteCourse;
                });
    }

    @Override
    public OnsiteCourse getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
              //  mapOnsiteCourses(result,onsiteCourses);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return null;
    }
    @Override
    public void create(OnsiteCourse onsiteCourse, Long onsiteCourseId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, onsiteCourseId);
            preparedStatement.setInt(2, onsiteCourse.getRoomNumber());
            //preparedStatement.setTimestamp(3, Timestamp.from(onsiteCourse.getDate()));

            //preparedStatement.setLong(4, onsiteCourse.setAttendances(attendances));

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                onsiteCourse.setId(generatedId);
                LOGGER.info("OnsiteCourse created with id: {}", generatedId);
            } else {
                LOGGER.warn("Failed to retrieve generated id for OnsiteCourse");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<OnsiteCourse> getAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<OnsiteCourse> onsiteCourses = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
               // mapOnsiteCourses(result);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return onsiteCourses;
    }
    @Override
    public void update(OnsiteCourse onsiteCourse) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, onsiteCourse.getRoomNumber());
           // preparedStatement.setTimestamp(2, onsiteCourse.getDate());
            preparedStatement.setLong(3, onsiteCourse.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("OnsiteCourse updated: {}", onsiteCourse);
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

            LOGGER.info("OnsiteCourse deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}






