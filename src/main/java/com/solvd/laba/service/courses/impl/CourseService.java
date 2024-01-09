package com.solvd.laba.service.courses.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.courses.info.Course;
import com.solvd.laba.domain.courses.info.Enrollment;
import com.solvd.laba.domain.courses.info.OnlineCourse;
import com.solvd.laba.domain.courses.info.OnsiteCourse;
import com.solvd.laba.persistence.courses.ICourseDAO;
import com.solvd.laba.persistence.courses.impl.CourseDAO;
import com.solvd.laba.persistence.courses.impl.OnsiteCourseDAO;
import com.solvd.laba.persistence.courses.impl.mybatis.CourseMyBatisImpl;
import com.solvd.laba.service.courses.ICourseService;
import com.solvd.laba.service.courses.IEnrollmentService;
import com.solvd.laba.service.courses.IOnlineCourseService;
import com.solvd.laba.service.courses.IOnsiteCourseService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class CourseService implements ICourseService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final ICourseDAO courseDAO;
    private final IOnsiteCourseService onsiteCourseService;
    private final IOnlineCourseService onlineCourseService;
    private final IEnrollmentService enrollmentService;

    public CourseService() {

        if (Config.IMPL.getValue().equals("jdbc")) {
           courseDAO = new CourseDAO();
           onsiteCourseService = new OnsiteCourseService();
           onlineCourseService = new OnlineCourseService();
           enrollmentService = new EnrollmentService();
        } else if (Config.IMPL.getValue().equals("myBatis")) {
            courseDAO = new CourseMyBatisImpl();
            onsiteCourseService = new OnsiteCourseService();
            onlineCourseService = new OnlineCourseService();
            enrollmentService = new EnrollmentService();
    } else {
            LOGGER.info("{}: Data source was not specified or is invalid. Defaulting to JDBC implementation", this.getClass().getSimpleName());
            this.courseDAO = new CourseDAO();
            this.onsiteCourseService = new OnsiteCourseService();
            this.onlineCourseService = new OnlineCourseService();
            this.enrollmentService = new EnrollmentService();
        }
    }

    @Override
    public void getCourseById(Long id) {
        courseDAO.getById(id);
    }

    @Override
    public void create(Course course) {

        courseDAO.create(course);
        onsiteCourseService.create((OnsiteCourse) course.getOnsiteCourses(), course.getId());
        onlineCourseService.create((OnlineCourse) course.getOnlineCourses(),course.getId());
        enrollmentService.create((Enrollment) course.getEnrollments(), course.getId());

        LOGGER.info("Course created: {}", course);
    }

    @Override
    public void update(Course course) {
        courseDAO.update(course);
    }

    @Override
    public void delete(long id) {
courseDAO.delete(id);
    }

    @Override
    public Course getById(long id) {
        return courseDAO.getById(id);
    }

    @Override
    public List<Course> getAll() {

        return courseDAO.getAll();
    }

}
