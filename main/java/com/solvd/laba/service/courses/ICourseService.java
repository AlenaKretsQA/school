package com.solvd.laba.service.courses;

import com.solvd.laba.domain.courses.info.Course;

import java.util.List;

public interface ICourseService {

    Course getCourseById(Long id);
    void create(Course course);
    void update(Course course);
    void delete(long id);
    void getById(Long id);
    List<Course> getAll();
}

