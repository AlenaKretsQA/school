package com.solvd.laba.persistence.courses_info;

import com.solvd.laba.domain.courses_info.OnsiteCourse;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IOnsiteCourseDAO extends CommonDAO<OnsiteCourse> {
    void addOnsiteCourse(OnsiteCourse onsiteCourse);
    OnsiteCourse getOnsiteCourseById(long id);
    List<OnsiteCourse> getAllOnsiteCourses();
    void updateOnsiteCourse(OnsiteCourse onsiteCourse);
    void deleteOnsiteCourse(long id);
}
