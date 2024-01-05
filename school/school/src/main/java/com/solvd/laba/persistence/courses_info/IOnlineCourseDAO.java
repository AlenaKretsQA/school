package com.solvd.laba.persistence.courses_info;

import com.solvd.laba.domain.courses_info.OnlineCourse;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IOnlineCourseDAO extends CommonDAO<OnlineCourse> {
    void addOnlineCourse(OnlineCourse onlineCourse);

    // Read
    OnlineCourse getOnlineCourseById(long id);
    List<OnlineCourse> getAllOnlineCourses();
    void updateOnlineCourse(OnlineCourse onlineCourse);
    void deleteOnlineCourse(long id);
}
