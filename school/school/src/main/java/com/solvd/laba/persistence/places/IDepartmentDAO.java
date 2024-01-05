package com.solvd.laba.persistence.places;

import com.solvd.laba.domain.places.Department;

import java.util.List;

public interface IDepartmentDAO extends CommonDAO<Department> {
    void addDepartment(Department department);
    // Read
    Department getDepartmentById(long id);
    List<Department> getAllDepartments();
    void updateDepartment(Department department);
    void deleteDepartment(long id);
}
