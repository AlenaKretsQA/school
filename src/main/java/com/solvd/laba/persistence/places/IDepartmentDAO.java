package com.solvd.laba.persistence.places;

import com.solvd.laba.domain.people.Teacher;
import com.solvd.laba.domain.places.Department;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;
import java.util.Set;

public interface IDepartmentDAO extends CommonDAO<Department> {
    void addDepartment(Department department);
    // Read
    Department getDepartmentById(long id);
    List<Department> getAllDepartments();
    void updateDepartment(Department department);
    void deleteDepartment(long id);

    Set<Teacher> getAllTeachersByDepartmentId(long id);

    Set<Department> getAllDepartmentsByTeacherId(long id);
}
