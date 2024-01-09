package com.solvd.laba;


import com.solvd.laba.domain.courses.info.Attendance;

import com.solvd.laba.domain.courses.info.Course;
import com.solvd.laba.domain.courses.info.Grade;
import com.solvd.laba.domain.courses.info.OnlineCourse;
import com.solvd.laba.persistence.courses.IAttendanceDAO;
import com.solvd.laba.persistence.courses.impl.AttendanceDAO;
import com.solvd.laba.service.courses.*;
import com.solvd.laba.service.courses.impl.*;
import com.solvd.laba.service.places.ISchoolService;
import com.solvd.laba.service.places.impl.SchoolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    public static void main(String[] args) {

    IAttendanceService attendanceService = new AttendanceService();
    ICourseService courseService = new CourseService();
    IEnrollmentService enrollmentService = new EnrollmentService();
    IGradeService gradeService = new GradeService();
    IOnlineCourseService onlineCourseService = new OnlineCourseService();
    IOnsiteCourseService onsiteCourseService = new OnsiteCourseService();


    //JDBC

        Attendance attendance1 = attendanceService.getAttendanceById(111L);
        Grade grade1 = gradeService.getGradeById(1L);
        Course course = courseService.getCourseById(1L);
        


        //MyBatis














        /*Department department = new Department();
        department.setName("Literature department");
        department.setId(78);
        Department newDepartment = new Department();
        newDepartment.setName("Computer Science");
        newDepartment.setSchoolId(1L);


        ITeacherDAO teacherDAO = new TeacherDAO();
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName("John");
        newTeacher.setLastName("Doe");
        teacherDAO.create(newTeacher);*/


        IAttendanceDAO attendanceDAO = new AttendanceDAO();
        Attendance newAttendance = new Attendance();
        newAttendance.setId(5);
        newAttendance.setStatus(String.valueOf(true));
        attendanceDAO.create(newAttendance,5);



    }
}