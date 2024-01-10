package com.solvd.laba.parsers.model;


import com.solvd.laba.domain.courses.info.Enrollment;
import com.solvd.laba.domain.courses.info.OnlineCourse;
import com.solvd.laba.domain.courses.info.OnsiteCourse;
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
    private double credit;

    private Set<OnlineCourse> onlineCourses;
    private Set<OnsiteCourse> onsiteCourses;
    private Set<Enrollment> enrollments;

}
