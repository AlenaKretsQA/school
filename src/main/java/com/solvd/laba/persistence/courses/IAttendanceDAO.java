package com.solvd.laba.persistence.courses;

import com.solvd.laba.domain.courses.info.Attendance;
import com.solvd.laba.persistence.CommonDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAttendanceDAO extends CommonDAO<Attendance> {

    void create (@Param("attendance")Attendance attendance,@Param("attendanceId") long attendanceId);
    void update (Attendance attendance, long attendanceId);
    void delete(long id);
    List<Attendance> getAll();
}

