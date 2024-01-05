package com.solvd.laba.service.courses_info.impl;

import com.solvd.laba.domain.courses_info.OnlineCourse;
import com.solvd.laba.persistence.courses_info.IOnlineCourseDAO;
import com.solvd.laba.service.courses_info.IOnlineCourseService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;
@AllArgsConstructor
public class OnlineCourseService implements IOnlineCourseService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    IOnlineCourseDAO iOnlineCourseDAO;

    @Override
    public void addOnlineCourse(OnlineCourse onlineCourse) {
iOnlineCourseDAO.addOnlineCourse(onlineCourse);
    }

    @Override
    public OnlineCourse getOnlineCourseById(long id) {

        return iOnlineCourseDAO.getOnlineCourseById(id);
    }

    @Override
    public List<OnlineCourse> getAllOnlineCourses() {
        return iOnlineCourseDAO.getAllOnlineCourses();
    }

    @Override
    public void updateOnlineCourse(OnlineCourse onlineCourse) {
     iOnlineCourseDAO.updateOnlineCourse(onlineCourse);
    }

    @Override
    public void deleteOnlineCourse(long id) {
      iOnlineCourseDAO.deleteOnlineCourse(id);
    }
}
