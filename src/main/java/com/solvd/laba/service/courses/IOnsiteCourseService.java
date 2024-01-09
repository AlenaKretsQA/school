package com.solvd.laba.service.courses;

import com.solvd.laba.domain.courses.info.OnsiteCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOnsiteCourseService {
    void create(@Param("onsiteCourse") OnsiteCourse onsiteCourse, @Param("onsiteCourseId") Long onsiteCourseId);
    void getOnsiteCourseById (Long id);
    List<OnsiteCourse> getAll();
    void update(OnsiteCourse onsiteCourse);
    void delete(long id);
}
