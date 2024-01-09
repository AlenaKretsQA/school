package com.solvd.laba.service.courses.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.courses.info.OnsiteCourse;
import com.solvd.laba.persistence.courses.IOnsiteCourseDAO;
import com.solvd.laba.persistence.courses.impl.OnsiteCourseDAO;
import com.solvd.laba.service.courses.IAttendanceService;
import com.solvd.laba.service.courses.IOnsiteCourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class OnsiteCourseService implements IOnsiteCourseService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final IOnsiteCourseDAO onsiteCourseDAO;
    private final IAttendanceService attendanceService;

    public OnsiteCourseService() {
        if (Config.IMPL.getValue().equals("jdbc")) {
            onsiteCourseDAO = new OnsiteCourseDAO();
            attendanceService = new AttendanceService();
        } else if (Config.IMPL.getValue().equals("myBatis")) {
            onsiteCourseDAO = new OnsiteCourseDAO();
            attendanceService = new AttendanceService();
        } else {
            LOGGER.info("{}: Data source was not specified or is invalid. Defaulting to JDBC implementation", this.getClass().getSimpleName());
            onsiteCourseDAO = new OnsiteCourseDAO();
            attendanceService = new AttendanceService();
        }
    }


    @Override
    public void create(OnsiteCourse onsiteCourse, Long onsiteCourseId) {
        onsiteCourseDAO.create(onsiteCourse,onsiteCourseId);
    }

    @Override
    public void getOnsiteCourseById(Long id) {
        onsiteCourseDAO.getById(id);
    }

    @Override
    public List<OnsiteCourse> getAll() {

        return onsiteCourseDAO.getAll();
    }

    @Override
    public void update(OnsiteCourse onsiteCourse) {
        onsiteCourseDAO.update(onsiteCourse);
    }

    @Override
    public void delete(long id) {
        onsiteCourseDAO.delete(id);
    }
}
