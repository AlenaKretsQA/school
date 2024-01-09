package com.solvd.laba.persistence.courses;

import com.solvd.laba.domain.courses.info.Enrollment;
import com.solvd.laba.persistence.CommonDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEnrollmentDAO extends CommonDAO<Enrollment> {

    void create(@Param("enrollment") Enrollment enrollment, @Param("enrollmentId") Long enrollmentId);
    List<Enrollment> getAllEnrollmentsByStudentId(Long studentId);
    void update(Enrollment enrollment);
    void delete(long id);
}
