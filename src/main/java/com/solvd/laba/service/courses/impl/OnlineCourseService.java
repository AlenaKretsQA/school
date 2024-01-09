package com.solvd.laba.service.courses.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.courses.info.OnlineCourse;
import com.solvd.laba.persistence.courses.IOnlineCourseDAO;
import com.solvd.laba.persistence.courses.IOnsiteCourseDAO;
import com.solvd.laba.persistence.courses.impl.OnlineCourseDAO;
import com.solvd.laba.service.courses.IGradeService;
import com.solvd.laba.service.courses.IOnlineCourseService;
import com.solvd.laba.service.courses.impl.GradeService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class OnlineCourseService implements IOnlineCourseService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final IOnlineCourseDAO onlineCourseDAO;
    private final IGradeService gradeService;

    public OnlineCourseService() {
        if (Config.IMPL.getValue().equals("jdbc")) {
            onlineCourseDAO = new OnlineCourseDAO();
            gradeService = new GradeService();
        } else if (Config.IMPL.getValue().equals("myBatis")) {
            onlineCourseDAO = new OnlineCourseDAO();
            gradeService = new GradeService();
        } else {
            LOGGER.info("{}: Data source was not specified or is invalid. Defaulting to JDBC implementation", this.getClass().getSimpleName());
            onlineCourseDAO = new OnlineCourseDAO();
            gradeService = new GradeService();
        }
    }

    @Override
    public void create(OnlineCourse onlineCourse, Long onlineCourseId) {
        onlineCourseDAO.create(onlineCourse, onlineCourseId);
    }

    @Override
    public void getOnlineCourseById(Long id) {
        onlineCourseDAO.getById(id);
    }

    @Override
    public List<OnlineCourse> getAll() {
        return onlineCourseDAO.getAll();
    }

    @Override
    public void update(OnlineCourse onlineCourse) {
        onlineCourseDAO.update(onlineCourse);
    }

    @Override
    public void delete(long id) {
        onlineCourseDAO.delete(id);
    }
}

