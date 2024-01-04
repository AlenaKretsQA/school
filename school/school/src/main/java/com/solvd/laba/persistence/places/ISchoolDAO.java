package com.solvd.laba.persistence.places;

import com.solvd.laba.domain.places.School;

import java.util.List;

public interface ISchoolDAO extends CommonDAO<School> {
    void addSchool(School school);
    // Read
    School getSchoolById(long id);
    List<School> getAllSchools();
    void updateSchool(School school);
    void deleteSchool(long id);
}
