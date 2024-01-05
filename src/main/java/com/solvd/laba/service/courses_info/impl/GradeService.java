package com.solvd.laba.service.courses_info.impl;

import com.solvd.laba.domain.courses_info.Grade;
import com.solvd.laba.persistence.courses_info.IGradeDAO;
import com.solvd.laba.service.courses_info.IGradeService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;
@AllArgsConstructor
public class GradeService implements IGradeService {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    IGradeDAO iGradeDAO;

    @Override
    public void addGrade(Grade grade) {
         iGradeDAO.addGrade(grade);
    }

    @Override
    public Grade getGradeById(long id) {

        return iGradeDAO.getGradeById(id);
    }

    @Override
    public List<Grade> getAllGrades() {

        return iGradeDAO.getAllGrades();
    }

    @Override
    public void updateGrade(Grade grade) {
iGradeDAO.updateGrade(grade);
    }

    @Override
    public void deleteGrade(long id) {
iGradeDAO.deleteGrade(id);
    }
}
