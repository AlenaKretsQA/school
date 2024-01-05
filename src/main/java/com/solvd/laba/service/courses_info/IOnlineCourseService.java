package com.solvd.laba.service.courses_info;

import com.solvd.laba.domain.courses_info.OnlineCourse;

import java.util.List;

public interface IOnlineCourseService {
    void addOnlineCourse(OnlineCourse onlineCourse);
    OnlineCourse getOnlineCourseById(long id);
    List<OnlineCourse> getAllOnlineCourses();
    void updateOnlineCourse(OnlineCourse onlineCourse);
    void deleteOnlineCourse(long id);
}
