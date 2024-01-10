package com.solvd.laba.persistence.courses;

import com.solvd.laba.domain.courses.info.OnsiteCourse;
import com.solvd.laba.persistence.CommonDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOnsiteCourseDAO extends CommonDAO<OnsiteCourse> {

    void create(@Param("onsiteCourse") OnsiteCourse onsiteCourse, @Param("onsiteCourseId") Long onsiteCourseId);
    List<OnsiteCourse> getAll();
    void update(OnsiteCourse onsiteCourse);
    void delete(long id);
}