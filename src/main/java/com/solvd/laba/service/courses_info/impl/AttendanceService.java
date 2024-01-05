package com.solvd.laba.service.courses_info.impl;

import com.solvd.laba.domain.courses_info.Attendance;
import com.solvd.laba.persistence.courses_info.IAttendanceDAO;
import com.solvd.laba.persistence.courses_info.IOnsiteCourseDAO;
import com.solvd.laba.service.courses_info.IAttendanceService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;
@AllArgsConstructor

public class AttendanceService implements IAttendanceService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    IAttendanceDAO iAttendanceDAO;

    @Override
    public void create(Attendance attendance, long attendanceId) {
        iAttendanceDAO.create(attendance,attendanceId);
    }

    @Override
    public void update(Attendance attendance, long attendanceId) {
         iAttendanceDAO.update(attendance,attendanceId);
    }

    @Override
    public void delete(long id) {
        iAttendanceDAO.delete(id);
    }

    @Override
    public List<Attendance> getAll() {
        return iAttendanceDAO.getAll();
    }
}
