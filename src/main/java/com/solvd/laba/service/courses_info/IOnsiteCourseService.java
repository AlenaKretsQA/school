package com.solvd.laba.service.courses_info;

import com.solvd.laba.domain.courses_info.OnsiteCourse;

import java.util.List;

public interface IOnsiteCourseService {
    void addOnsiteCourse(OnsiteCourse onsiteCourse);
    OnsiteCourse getOnsiteCourseById(long id);
    List<OnsiteCourse> getAllOnsiteCourses();
    void updateOnsiteCourse(OnsiteCourse onsiteCourse);
    void deleteOnsiteCourse(long id);
}
