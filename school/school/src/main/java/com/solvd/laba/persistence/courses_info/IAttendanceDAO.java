package com.solvd.laba.persistence.courses_info;

import com.solvd.laba.domain.courses_info.Attendance;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IAttendanceDAO extends CommonDAO<Attendance> {

    Attendance getById(long id);

    List<Attendance> getAll();

    void save(Attendance attendance);

    void update(Attendance attendance);

    void delete(long id);
}

