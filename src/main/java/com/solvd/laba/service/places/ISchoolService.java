package com.solvd.laba.service.places;

import com.solvd.laba.domain.places.School;

import java.util.List;

public interface ISchoolService {
    void addSchool(School school);
    School getSchoolById(long id);
    List<School> getAllSchools();
    void updateSchool(School school);
    void deleteSchool(long id);
}
