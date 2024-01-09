package com.solvd.laba.persistence.courses;

import com.solvd.laba.domain.courses.info.OnlineCourse;
import com.solvd.laba.persistence.CommonDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOnlineCourseDAO extends CommonDAO<OnlineCourse> {
    void create (@Param("onlineCourse")OnlineCourse onlineCourse, @Param("onlineCourseId")Long onlineCourseId);
    List<OnlineCourse> getAll();
    void update(OnlineCourse onlineCourse);
    void delete(long id);

}
