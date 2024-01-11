package com.solvd.laba.persistence.courses;

import com.solvd.laba.domain.courses.info.Course;
import com.solvd.laba.persistence.CommonDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICourseDAO extends CommonDAO<Course> {

    void create(@Param("course")Course course);
    void update(Course course);
    void delete(long id);
    List<Course> getAll();
}

