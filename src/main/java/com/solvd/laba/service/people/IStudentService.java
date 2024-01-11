package com.solvd.laba.service.people;

import com.solvd.laba.domain.people.Student;

import java.util.List;

public interface IStudentService {
    void addrStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getAllStudents();
}
