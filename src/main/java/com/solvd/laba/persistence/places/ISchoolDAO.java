package com.solvd.laba.persistence.places;

import com.solvd.laba.domain.places.School;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface ISchoolDAO extends CommonDAO<School> {
    void addSchool(School school);
    School getSchoolById(long id);
    List<School> getAllSchools();
    void updateSchool(School school);
    void deleteSchool(long id);
}
