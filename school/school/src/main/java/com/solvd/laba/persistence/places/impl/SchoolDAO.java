package com.solvd.laba.persistence.places.impl;

import com.solvd.laba.domain.places.School;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.places.ISchoolDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class SchoolDAO implements ISchoolDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String ADD_SCHOOL_QUERY = "INSERT INTO schools (id, school_name, location, established_date) VALUES (?, ?, ?, ?)";
    private static final String GET_SCHOOL_BY_ID_QUERY = "SELECT * FROM schools WHERE id = ?";
    private static final String GET_ALL_SCHOOLS_QUERY = "SELECT * FROM schools";
    private static final String UPDATE_SCHOOL_QUERY = "UPDATE schools SET school_name = ?, location = ?, established_date = ? WHERE id = ?";
    private static final String DELETE_SCHOOL_QUERY = "DELETE FROM schools WHERE id = ?";


    @Override
    public void addSchool(School school) {
    }
    @Override
    public School getSchoolById(long id) {
        return null;
    }

    @Override
    public List<School> getAllSchools() {
        return null;
    }

    @Override
    public void updateSchool(School school) {
    }
    @Override
    public void deleteSchool(long id) {

    }
}
