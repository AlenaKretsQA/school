package com.solvd.laba.service.courses;

import com.solvd.laba.domain.courses.info.Attendance;

import java.util.List;

public interface IAttendanceService {

    void getAttendanceById(Long id);
    void create(Attendance attendance,long attendanceId);

    void update(Attendance attendance,long attendanceId);

    void delete(long id);
    List<Attendance> getAll();
}
