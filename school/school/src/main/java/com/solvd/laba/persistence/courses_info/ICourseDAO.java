package com.solvd.laba.persistence.courses_info;

import com.solvd.laba.domain.courses_info.Course;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface ICourseDAO extends CommonDAO<Course> {

    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(long id);

    // Read
    Course getCourseById(long id);
    List<Course> getAllCourses();
}

