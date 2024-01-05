package com.solvd.laba.service.places.impl;

import com.solvd.laba.domain.places.School;
import com.solvd.laba.persistence.places.ISchoolDAO;
import com.solvd.laba.service.places.ISchoolService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

@AllArgsConstructor
public class SchoolService implements ISchoolService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    ISchoolDAO iSchoolDAO;

    @Override
    public void addSchool(School school) {
        iSchoolDAO.addSchool(school);
    }

    @Override
    public School getSchoolById(long id) {
        return iSchoolDAO.getSchoolById(id);
    }

    @Override
    public List<School> getAllSchools() {
        return iSchoolDAO.getAllSchools();
    }

    @Override
    public void updateSchool(School school) {
iSchoolDAO.updateSchool(school);
    }

    @Override
    public void deleteSchool(long id) {
iSchoolDAO.deleteSchool(id);
    }
}
