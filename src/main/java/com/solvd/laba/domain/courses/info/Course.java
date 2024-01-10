package com.solvd.laba.domain.courses.info;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {

    private long id;
    private String courseName;
    private long credit;

    private Set<OnlineCourse> onlineCourses;
    private Set<OnsiteCourse> onsiteCourses;
    private Set<Enrollment> enrollments;

}
