package com.solvd.laba.service.places;

import com.solvd.laba.domain.people.Teacher;
import com.solvd.laba.domain.places.Department;

import java.util.List;
import java.util.Set;

public interface IDepartmentService {
    void addDepartment(Department department);
    // Read
    Department getDepartmentById(long id);
    List<Department> getAllDepartments();
    void updateDepartment(Department department);
    void deleteDepartment(long id);

    Set<Teacher> getAllTeachersByDepartmentId(long id);

    Set<Department> getAllDepartmentsByTeacherId(long id);
}
