package com.solvd.laba.service.courses_info.impl;

import com.solvd.laba.domain.courses_info.OnsiteCourse;
import com.solvd.laba.persistence.courses_info.IOnlineCourseDAO;
import com.solvd.laba.persistence.courses_info.IOnsiteCourseDAO;
import com.solvd.laba.service.courses_info.IOnsiteCourseService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;
@AllArgsConstructor
public class OnsiteCourseService implements IOnsiteCourseService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    IOnsiteCourseDAO iOnsiteCourseDAO;

    @Override
    public void addOnsiteCourse(OnsiteCourse onsiteCourse) {
iOnsiteCourseDAO.addOnsiteCourse(onsiteCourse);
    }

    @Override
    public OnsiteCourse getOnsiteCourseById(long id) {

        return iOnsiteCourseDAO.getOnsiteCourseById(id);
    }

    @Override
    public List<OnsiteCourse> getAllOnsiteCourses() {

        return iOnsiteCourseDAO.getAllOnsiteCourses();
    }

    @Override
    public void updateOnsiteCourse(OnsiteCourse onsiteCourse) {
    iOnsiteCourseDAO.updateOnsiteCourse(onsiteCourse);
    }

    @Override
    public void deleteOnsiteCourse(long id) {
iOnsiteCourseDAO.deleteOnsiteCourse(id);
    }
}
