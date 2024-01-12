package com.solvd.laba.domain.places;

import com.solvd.laba.domain.people.Student;
import com.solvd.laba.domain.people.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Department {
    private long id;
    private String name;
    private long schoolId;
    private Set<Teacher> teachers;
    private Set<Student> students;
}
