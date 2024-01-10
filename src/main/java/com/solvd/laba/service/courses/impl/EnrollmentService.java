package com.solvd.laba.service.courses.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.courses.info.Enrollment;
import com.solvd.laba.persistence.courses.IEnrollmentDAO;
import com.solvd.laba.persistence.courses.impl.EnrollmentDAO;
import com.solvd.laba.service.courses.IEnrollmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class EnrollmentService implements IEnrollmentService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final IEnrollmentDAO enrollmentDAO;


    public EnrollmentService() {
        if (Config.IMPL.getValue().equals("jdbc")) {
            enrollmentDAO = new EnrollmentDAO();
        } else if (Config.IMPL.getValue().equals("myBatis")) {
            enrollmentDAO = new EnrollmentDAO();
        } else {
            LOGGER.info("{}: Data source was not specified or is invalid. Defaulting to JDBC implementation", this.getClass().getSimpleName());
            enrollmentDAO = new EnrollmentDAO();
        }
    }

    @Override
    public void create(Enrollment enrollment, Long enrollmentId) {
        enrollmentDAO.create(enrollment, enrollmentId);
    }

    @Override
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentDAO.getById(id);
    }

    @Override
    public List<Enrollment> getAllEnrollmentsByStudentId(Long studentId) {
        return  enrollmentDAO.getAllEnrollmentsByStudentId(studentId);
    }

    @Override
    public void update(Enrollment enrollment) {
        enrollmentDAO.update(enrollment);
    }

    @Override
    public void delete(long id) {
        enrollmentDAO.delete(id);
    }
}
