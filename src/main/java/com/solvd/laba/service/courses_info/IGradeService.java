package com.solvd.laba.service.courses_info;

import com.solvd.laba.domain.courses_info.Grade;

import java.util.List;

public interface IGradeService {
    void addGrade(Grade grade);
    Grade getGradeById(long id);
    List<Grade> getAllGrades();
    void updateGrade(Grade grade);
    void deleteGrade(long id);
}
