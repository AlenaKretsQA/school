package com.solvd.laba.persistence.places.impl;

import com.solvd.laba.domain.places.Department;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.places.IDepartmentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class DepartmentDAO implements IDepartmentDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String ADD_DEPARTMENT_QUERY = "INSERT INTO departments (id, name, school_id) VALUES (?, ?, ?)";
    private static final String GET_DEPARTMENT_BY_ID_QUERY = "SELECT * FROM departments WHERE id = ?";
    private static final String GET_ALL_DEPARTMENTS_QUERY = "SELECT * FROM departments";
    private static final String UPDATE_DEPARTMENT_QUERY = "UPDATE departments SET name = ?, school_id = ? WHERE id = ?";
    private static final String DELETE_DEPARTMENT_QUERY = "DELETE FROM departments WHERE id = ?";

    @Override
    public void addDepartment(Department department) {

    }

    @Override
    public Department getDepartmentById(long id) {
        return null;
    }

    @Override
    public List<Department> getAllDepartments() {
        return null;
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(long id) {

    }
}
