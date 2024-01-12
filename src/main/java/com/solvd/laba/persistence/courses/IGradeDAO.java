package com.solvd.laba.persistence.courses;


import com.solvd.laba.domain.courses.info.Grade;
import com.solvd.laba.persistence.CommonDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGradeDAO extends CommonDAO<Grade> {

    void create(@Param("grade") Grade grade, @Param("gradeId") Long gradeId);
    List<Grade> getAll();
    void update(Grade grade);
    void delete(long id);
}
