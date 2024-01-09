package com.solvd.laba.service.courses;

import com.solvd.laba.domain.courses.info.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGradeService {

    Grade getGradeById(Long id);
    void create(@Param("grade") Grade grade, @Param("gradeId") Long gradeId);
    List<Grade> getAll();
    void update(int grade);
    void delete(long id);
}
