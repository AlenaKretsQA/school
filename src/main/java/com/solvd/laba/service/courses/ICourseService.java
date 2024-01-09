package com.solvd.laba.service.courses;

import com.solvd.laba.domain.courses.info.Course;

import java.util.List;

public interface ICourseService {

    void getCourseById(Long id);
    void create(Course course);
    void update(Course course);
    void delete(long id);
    Course getById(long id);
    List<Course> getAll();
}

