package com.solvd.laba.service.courses_info.impl;

import com.solvd.laba.domain.courses_info.Enrollment;
import com.solvd.laba.persistence.courses_info.IEnrollmentDAO;
import com.solvd.laba.service.courses_info.IEnrollmentService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;
@AllArgsConstructor
public class EnrollmentService implements IEnrollmentService {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    IEnrollmentDAO iEnrollmentDAO;


    @Override
    public void addEnrollment(Enrollment enrollment) {
     iEnrollmentDAO.addEnrollment(enrollment);
    }

    @Override
    public Enrollment getEnrollmentById(long id) {

        return iEnrollmentDAO.getEnrollmentById(id);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {

        return iEnrollmentDAO.getAllEnrollments();
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {
iEnrollmentDAO.updateEnrollment(enrollment);
    }

    @Override
    public void deleteEnrollment(long id) {
iEnrollmentDAO.deleteEnrollment(id);
    }
}
