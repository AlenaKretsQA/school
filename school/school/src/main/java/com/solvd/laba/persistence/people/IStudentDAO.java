package com.solvd.laba.persistence.people;


import com.solvd.laba.domain.people.Student;

import java.util.Set;

public interface IStudentDAO extends IPersonDAO<Student> {
    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(long id);


    // Read
    Student getStudentById(long id);
    Set<Student> getAllStudents();

}
