package com.solvd.laba.domain.courses.info;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {

    private long id;
    private String courseName;
    private long credit;

    private List<OnlineCourse> onlineCourses;
    private List<OnsiteCourse> onsiteCourses;
    private List<Enrollment> enrollments;

}
