package com.solvd.laba.parsers.stax;

import com.solvd.laba.domain.courses.info.Grade;
import com.solvd.laba.parsers.model.Course;
import com.solvd.laba.parsers.model.OnlineCourse;
import com.solvd.laba.parsers.model.OnsiteCourse;
import com.solvd.laba.parsers.model.Attendance;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XMLCourseParser {

    private StringBuilder currentElementValue;
    private Course course;
    private OnlineCourse currentOnlineCourse;
    private OnsiteCourse currentOnsiteCourse;
    private Attendance currentAttendance;
    private List<OnlineCourse> onlineCourses;
    private List<OnsiteCourse> onsiteCourses;
    private List<Attendance> attendances;

    public Course parse(String xmlFilePath) {
        course = new Course();
        onlineCourses = new ArrayList<>();
        onsiteCourses = new ArrayList<>();
        attendances = new ArrayList<>();
        currentElementValue = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(xmlFilePath)) {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fis);

            while (xmlStreamReader.hasNext()) {
                int event = xmlStreamReader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement(xmlStreamReader);
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        characters(xmlStreamReader);
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        endElement(xmlStreamReader);
                        break;
                }
            }
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }

        return course;
    }

    public Course getCurrentCourse() {
        return course;
    }

    private void startElement(XMLStreamReader xmlStreamReader) {
        String elementName = xmlStreamReader.getLocalName();

        if ("onlineCourse".equals(elementName)) {
            currentOnlineCourse = new OnlineCourse();
        } else if ("onsiteCourse".equals(elementName)) {
            currentOnsiteCourse = new OnsiteCourse();
        } else if ("attendances".equals(elementName)) {
            currentAttendance = new Attendance();
        }

        currentElementValue.setLength(0);
    }

    private void characters(XMLStreamReader xmlStreamReader) {
        currentElementValue.append(xmlStreamReader.getText().trim());
    }

    private void endElement(XMLStreamReader xmlStreamReader) {
        String endElementName = xmlStreamReader.getLocalName();

        if ("onlineCourse".equals(endElementName)) {
            onlineCourses.add(currentOnlineCourse);
            currentOnlineCourse = null;
        } else if ("onsiteCourse".equals(endElementName)) {
            onsiteCourses.add(currentOnsiteCourse);
            currentOnsiteCourse = null;
        } else if ("attendances".equals(endElementName)) {
            attendances.add(currentAttendance);
            currentAttendance = null;
        } else {
            handleEndElement(endElementName);
        }
    }

    private void handleEndElement(String endElementName) {
        switch (endElementName) {
            case "id":
                if (currentOnlineCourse != null) {
                    currentOnlineCourse.setId(Long.parseLong(currentElementValue.toString()));
                } else if (currentOnsiteCourse != null) {
                    currentOnsiteCourse.setId(Long.parseLong(currentElementValue.toString()));
                } else if (currentAttendance != null) {
                    currentAttendance.setId(Long.parseLong(currentElementValue.toString()));
                } else {
                    course.setId(Long.parseLong(currentElementValue.toString()));
                }
                break;
            case "courseName":
                course.setCourseName(currentElementValue.toString());
                break;
            case "credit":
                course.setCredit(Double.parseDouble(currentElementValue.toString()));
                break;
            case "url":
                currentOnlineCourse.setUrl(currentElementValue.toString());
                break;
            case "gradeId":
                currentOnlineCourse.setGradeId(new Grade());
                break;
            case "roomNumber":
                currentOnsiteCourse.setRoomNumber(Integer.parseInt(currentElementValue.toString()));
                break;
            case "date":
                currentOnsiteCourse.setDate(LocalDate.parse(currentElementValue));
                break;
            case "status":
                currentAttendance.setStatus(currentElementValue.toString());
                break;
        }
    }
}



