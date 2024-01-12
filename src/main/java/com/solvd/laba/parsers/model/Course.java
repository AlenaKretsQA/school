package com.solvd.laba.parsers.model;


import com.solvd.laba.domain.courses.info.Enrollment;
import com.solvd.laba.domain.courses.info.OnlineCourse;
import com.solvd.laba.domain.courses.info.OnsiteCourse;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;
@Getter
@Setter
@ToString
@NoArgsConstructor

@XmlRootElement(name = "course")
@XmlAccessorType(XmlAccessType.FIELD)
public class Course {

    private long id;
    private String courseName;
    private double credit;

    @XmlElementWrapper(name = "onlineCourses")
    @XmlElement(name = "onlineCourse")
    private List<OnlineCourse> onlineCourses;

    @XmlElementWrapper(name = "onsiteCourses")
    @XmlElement(name = "onsiteCourse")
    private List<OnsiteCourse> onsiteCourses;

    @XmlElementWrapper(name = "enrollments")
    @XmlElement(name = "enrollment")
    private List<Enrollment> enrollments;

}
