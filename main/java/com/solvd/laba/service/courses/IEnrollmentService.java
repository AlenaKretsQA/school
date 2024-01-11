package com.solvd.laba.service.courses;

import com.solvd.laba.domain.courses.info.Enrollment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEnrollmentService {
    void create(@Param("enrollment") Enrollment enrollment, @Param("enrollmentId") Long enrollmentId);
    Enrollment getEnrollmentById (Long id);
    List<Enrollment> getAllEnrollmentsByStudentId(Long studentId);
    void update(Enrollment enrollment);
    void delete(long id);
}
