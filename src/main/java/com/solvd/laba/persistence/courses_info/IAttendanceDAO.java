package com.solvd.laba.persistence.courses_info;

import com.solvd.laba.domain.courses_info.Attendance;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IAttendanceDAO extends CommonDAO<Attendance> {

    void create(Attendance attendance,long attendanceId);
    void update(Attendance attendance,long attendanceId);
    void delete(long id);
    List<Attendance> getAll();
}

