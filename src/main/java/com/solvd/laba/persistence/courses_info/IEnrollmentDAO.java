package com.solvd.laba.persistence.courses_info;

import com.solvd.laba.domain.courses_info.Enrollment;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IEnrollmentDAO extends CommonDAO<Enrollment> {

    void addEnrollment(Enrollment enrollment);
    Enrollment getEnrollmentById(long id);
    List<Enrollment> getAllEnrollments();
    void updateEnrollment(Enrollment enrollment);
    void deleteEnrollment(long id);
}
