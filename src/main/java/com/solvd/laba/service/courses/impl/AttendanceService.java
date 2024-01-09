package com.solvd.laba.service.courses.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.courses.info.Attendance;
import com.solvd.laba.persistence.courses.IAttendanceDAO;
import com.solvd.laba.persistence.courses.impl.AttendanceDAO;
import com.solvd.laba.persistence.courses.impl.mybatis.AttendanceMyBatisImpl;
import com.solvd.laba.service.courses.IAttendanceService;
import com.solvd.laba.service.courses.IOnlineCourseService;
import com.solvd.laba.service.courses.IOnsiteCourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;


public class AttendanceService implements IAttendanceService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final IAttendanceDAO attendanceDAO;


    public AttendanceService() {

        if (Config.IMPL.getValue().equals("jdbc")) {
            attendanceDAO = new AttendanceDAO();
        } else if (Config.IMPL.getValue().equals("myBatis")) {
            attendanceDAO = new AttendanceMyBatisImpl();
        } else {
            LOGGER.info("{}: Data source was not specified or is invalid. Defaulting to JDBC implementation", this.getClass().getSimpleName());
            attendanceDAO = new AttendanceDAO();
        }
    }

    @Override
    public void getAttendanceById(Long id) {
        attendanceDAO.getById(id);
    }

    @Override
    public void create(Attendance attendance, long attendanceId) {

        attendanceDAO.create(attendance,attendanceId);
    }

    @Override
    public void update(Attendance attendance, long attendanceId) {
     attendanceDAO.update(attendance,attendanceId);
    }

    @Override
    public void delete(long id) {
        attendanceDAO.delete(id);
    }

    @Override
    public List<Attendance> getAll() {
        return null;
    }
}
