package com.solvd.laba.service.courses_info;

import com.solvd.laba.domain.courses_info.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    void addEnrollment(Enrollment enrollment);
    Enrollment getEnrollmentById(long id);
    List<Enrollment> getAllEnrollments();
    void updateEnrollment(Enrollment enrollment);
    void deleteEnrollment(long id);
}
