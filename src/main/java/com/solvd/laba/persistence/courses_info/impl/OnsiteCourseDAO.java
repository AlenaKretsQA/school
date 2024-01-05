package com.solvd.laba.persistence.courses_info.impl;

import com.solvd.laba.domain.courses_info.OnsiteCourse;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.courses_info.IOnsiteCourseDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OnsiteCourseDAO implements IOnsiteCourseDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String ADD_ONSITE_COURSE_QUERY = "INSERT INTO onsite_courses (id, room_number, date, course_id) VALUES (?, ?, ?, ?)";
    private static final String GET_ONSITE_COURSE_BY_ID_QUERY = "SELECT * FROM onsite_courses WHERE id = ?";
    private static final String GET_ALL_ONSITE_COURSES_QUERY = "SELECT * FROM onsite_courses";
    private static final String UPDATE_ONSITE_COURSE_QUERY = "UPDATE onsite_courses SET room_number = ?, date = ?, course_id = ? WHERE id = ?";
    private static final String DELETE_ONSITE_COURSE_QUERY = "DELETE FROM onsite_courses WHERE id = ?";

    @Override
    public OnsiteCourse getById(Long id) {
        return null;
    }

    @Override
    public void update(OnsiteCourse object) {

    }

    @Override
    public void addOnsiteCourse(OnsiteCourse onsiteCourse) {
    Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ONSITE_COURSE_QUERY)) {
        preparedStatement.setLong(1, onsiteCourse.getId());
        //preparedStatement.setByte(2, onsiteCourse.getRoomNumber());
        //preparedStatement.setTimestamp(3, onsiteCourse.getDate());
        //preparedStatement.setLong(4, onsiteCourse.getCourseId());

        preparedStatement.executeUpdate();

        LOGGER.info("Onsite Course added: {}", onsiteCourse);

    } catch (SQLException e) {
        LOGGER.error(e.getMessage());
    } finally {
        CONNECTION_POOL.releaseConnection(connection);
    }
}

    @Override
    public OnsiteCourse getOnsiteCourseById(long id) {
        return null;
    }

    @Override
    public List<OnsiteCourse> getAllOnsiteCourses() {
        return null;
    }

    @Override
    public void updateOnsiteCourse(OnsiteCourse onsiteCourse) {

    }

    @Override
    public void deleteOnsiteCourse(long id) {

    }
}
