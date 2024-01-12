package com.solvd.laba.service.people.impl;

import com.solvd.laba.domain.people.Teacher;
import com.solvd.laba.persistence.people.ITeacherDAO;
import com.solvd.laba.persistence.people.impl.TeacherDAO;
import com.solvd.laba.service.people.ITeacherService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

@AllArgsConstructor
public class TeacherService implements ITeacherService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
     ITeacherDAO iTeacherDAO;

    @Override
    public void addTeacher(Teacher teacher) {
        iTeacherDAO.addTeacher(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
    iTeacherDAO.deleteTeacher(id);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return iTeacherDAO.getAllTeachers();
    }
}
