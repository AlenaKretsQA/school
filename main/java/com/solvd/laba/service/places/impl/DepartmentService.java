package com.solvd.laba.service.places.impl;

import com.solvd.laba.domain.people.Teacher;
import com.solvd.laba.domain.places.Department;
import com.solvd.laba.persistence.places.IDepartmentDAO;
import com.solvd.laba.service.places.IDepartmentService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class DepartmentService implements IDepartmentService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    IDepartmentDAO iDepartmentDAO;

    @Override
    public void addDepartment(Department department) {
        iDepartmentDAO.addDepartment(department);
    }

    @Override
    public Department getDepartmentById(long id) {
        return iDepartmentDAO.getDepartmentById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return iDepartmentDAO.getAllDepartments();
    }

    @Override
    public void updateDepartment(Department department) {
iDepartmentDAO.updateDepartment(department);
    }

    @Override
    public void deleteDepartment(long id) {
iDepartmentDAO.deleteDepartment(id);
    }

    @Override
    public Set<Teacher> getAllTeachersByDepartmentId(long id) {
        return iDepartmentDAO.getAllTeachersByDepartmentId(id);
    }

    @Override
    public Set<Department> getAllDepartmentsByTeacherId(long id) {
        return iDepartmentDAO.getAllDepartmentsByTeacherId(id);
    }
}
