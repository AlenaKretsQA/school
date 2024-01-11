package com.solvd.laba.service.courses;

import com.solvd.laba.domain.courses.info.OnlineCourse;
import com.solvd.laba.domain.courses.info.OnsiteCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOnlineCourseService {

    void create (@Param("onlineCourse")OnlineCourse onlineCourse, @Param("onlineCourseId")Long onlineCourseId);
    OnlineCourse getOnlineCourseById (Long id);
    List<OnlineCourse> getAll();
    void update(OnlineCourse onlineCourse);
    void delete(long id);
}
