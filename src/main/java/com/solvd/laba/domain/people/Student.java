package com.solvd.laba.domain.people;

import com.solvd.laba.domain.courses.info.Enrollment;
import com.solvd.laba.domain.places.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class Student extends Person {
    private Date birthdate;
    private Department department;
    private Set<Parent> parents;
    private Set<Enrollment> enrollments;
}
