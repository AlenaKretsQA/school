package com.solvd.laba.service.people.impl;

import com.solvd.laba.domain.people.Student;
import com.solvd.laba.persistence.people.IStudentDAO;
import com.solvd.laba.persistence.people.ITeacherDAO;
import com.solvd.laba.service.people.IStudentService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

@AllArgsConstructor
public class StudentService implements IStudentService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    IStudentDAO iStudentDAO;

    @Override
    public void addrStudent(Student student) {
        iStudentDAO.addStudent(student);
    }

    @Override
    public void deleteStudent(Long id) {
        iStudentDAO.deleteStudent(id);
    }
    @Override
    public List<Student> getAllStudents() {

        return (List<Student>) iStudentDAO.getAllStudents();
    }
}
