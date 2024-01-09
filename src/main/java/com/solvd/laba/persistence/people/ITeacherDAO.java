package com.solvd.laba.persistence.people;
import com.solvd.laba.domain.people.Teacher;

import java.util.List;

public interface ITeacherDAO extends IPersonDAO<Teacher> {

    void create(Teacher teacher);
    void addTeacher(Teacher teacher);
    void deleteTeacher(Long id);
    List<Teacher> getAllTeachers();
    void update(Teacher teacher);
}
