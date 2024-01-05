package com.solvd.laba.service.courses_info;

import com.solvd.laba.domain.courses_info.Attendance;

import java.util.List;

public interface IAttendanceService {
    void create(Attendance attendance,long attendanceId);

    void update(Attendance attendance,long attendanceId);

    void delete(long id);
    List<Attendance> getAll();
}
