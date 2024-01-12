package com.solvd.laba.persistence.courses.impl.mybatis;

import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.courses.info.Attendance;
import com.solvd.laba.persistence.courses.IAttendanceDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AttendanceMyBatisImpl implements IAttendanceDAO {

    @Override
    public Attendance getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IAttendanceDAO attendanceDAO = sqlSession.getMapper(IAttendanceDAO.class);
            return attendanceDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Attendance attendance, long attendanceId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IAttendanceDAO attendanceDAO = sqlSession.getMapper(IAttendanceDAO.class);
            attendanceDAO.create(attendance, attendanceId);
        }
    }

    @Override
    public void update(Attendance attendance, long attendanceId) {

    }

    @Override
    public void update(Attendance attendance) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IAttendanceDAO attendanceDAO = sqlSession.getMapper(IAttendanceDAO.class);
            attendanceDAO.update(attendance);
        }
    }

    @Override
    public void delete(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IAttendanceDAO attendanceDAO = sqlSession.getMapper(IAttendanceDAO.class);
            attendanceDAO.delete(id);
        }
    }

    @Override
    public List<Attendance> getAll() {
        return null;
    }

}
