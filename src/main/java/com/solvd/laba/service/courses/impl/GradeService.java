package com.solvd.laba.service.courses.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.courses.info.Grade;
import com.solvd.laba.persistence.courses.IGradeDAO;
import com.solvd.laba.persistence.courses.impl.GradeDAO;
import com.solvd.laba.service.courses.IGradeService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;


public class GradeService implements IGradeService {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final IGradeDAO gradeDAO;

    public GradeService() {
        if (Config.IMPL.getValue().equals("jdbc")) {
            gradeDAO = new GradeDAO();
        } else if (Config.IMPL.getValue().equals("myBatis")) {
            gradeDAO = new GradeDAO();
        } else {
            LOGGER.info("{}: Data source was not specified or is invalid. Defaulting to JDBC implementation", this.getClass().getSimpleName());
            gradeDAO = new GradeDAO();
        }
    }


    @Override
    public void getGradeById(Long id) {
        gradeDAO.getById(id);
    }

    @Override
    public void create(Grade grade, Long gradeId) {
        gradeDAO.create(grade,gradeId);
    }

    @Override
    public List<Grade> getAll() {

        return gradeDAO.getAll();
    }

    @Override
    public void update(int grade) {
        gradeDAO.update(grade);
    }

    @Override
    public void delete(long id) {
        gradeDAO.delete(id);
    }
}

