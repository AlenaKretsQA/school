package com.solvd.laba.service.courses_info.impl;

import com.solvd.laba.domain.courses_info.Course;
import com.solvd.laba.persistence.courses_info.ICourseDAO;
import com.solvd.laba.service.courses_info.ICourseService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;
@AllArgsConstructor
public class CourseService implements ICourseService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    ICourseDAO iCourseDAO;

    @Override
    public void create(Course course) {
iCourseDAO.create(course);
    }

    @Override
    public void update(Course course) {
iCourseDAO.update(course);
    }

    @Override
    public void delete(long id) {
iCourseDAO.delete(id);
    }

    @Override
    public Course getById(long id) {

      return iCourseDAO.getById(id);
    }

    @Override
    public List<Course> getAll() {

        return iCourseDAO.getAll();
    }
}
