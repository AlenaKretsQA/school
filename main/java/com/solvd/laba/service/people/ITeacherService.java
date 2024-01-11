package com.solvd.laba.service.people;

import com.solvd.laba.domain.people.Teacher;

import java.util.List;

public interface ITeacherService {

    void addTeacher(Teacher teacher);
    void deleteTeacher(Long id);
    List<Teacher> getAllTeachers();
}
