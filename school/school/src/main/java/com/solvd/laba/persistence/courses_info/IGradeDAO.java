package com.solvd.laba.persistence.courses_info;


import com.solvd.laba.domain.courses_info.Grade;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IGradeDAO extends CommonDAO<Grade> {

    void addGrade(Grade grade);
    // Read
    Grade getGradeById(long id);
    List<Grade> getAllGrades();
    void updateGrade(Grade grade);
    void deleteGrade(long id);
}
