package com.solvd.laba.persistence.courses_info;

import com.solvd.laba.domain.courses_info.Course;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface ICourseDAO extends CommonDAO<Course> {

    void create(Course course);
    void update(Course course);
    void delete(long id);
    Course getById(long id);
    List<Course> getAll();
}

