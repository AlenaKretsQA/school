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
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaxMain {
    public static void main(String[] args) {
        String xmlFilePath = "src/main/resources/parsers/xml/course.xml";
        String xsdFilePath = "src/main/resources/parsers/xml/course.xsd";

        // Validate XML against XSD
        validateXML(xmlFilePath, xsdFilePath);

        // Parse XML using StAX
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fis = new FileInputStream(xmlFilePath)) {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fis);

            Course course = parseXML(xmlStreamReader);

            // Perform operations with the parsed course object as needed
            System.out.println(course);

        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    private static void validateXML(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new StreamSource(new File(xsdFilePath)));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilePath)));

            System.out.println("XML validation success");

        } catch (Exception e) {
            System.err.println("XML validation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Course parseXML(XMLStreamReader xmlStreamReader) throws XMLStreamException {
        StringBuilder currentElementValue = new StringBuilder();
        Course course = new Course();
        List<OnlineCourse> onlineCourses = new ArrayList<>();
        List<OnsiteCourse> onsiteCourses = new ArrayList<>();
        List<Attendance> attendances = new ArrayList<>();
        OnlineCourse currentOnlineCourse = null;
        OnsiteCourse currentOnsiteCourse = null;
        Attendance currentAttendance = null;

        while (xmlStreamReader.hasNext()) {
            int event = xmlStreamReader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    startElement(xmlStreamReader, currentElementValue, course, onlineCourses, onsiteCourses, attendances,
                            currentOnlineCourse, currentOnsiteCourse, currentAttendance);
                    break;

                case XMLStreamConstants.CHARACTERS:
                    characters(xmlStreamReader, currentElementValue);
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    endElement(xmlStreamReader, currentElementValue, course, onlineCourses, onsiteCourses, attendances,
                            currentOnlineCourse, currentOnsiteCourse, currentAttendance);
                    break;
            }
        }

        return course;
    }

    private static void startElement(XMLStreamReader xmlStreamReader, StringBuilder currentElementValue,
                                     Course course, List<OnlineCourse> onlineCourses, List<OnsiteCourse> onsiteCourses,
                                     List<Attendance> attendances, OnlineCourse currentOnlineCourse,
                                     OnsiteCourse currentOnsiteCourse, Attendance currentAttendance) {
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

    private static void characters(XMLStreamReader xmlStreamReader, StringBuilder currentElementValue) {
        currentElementValue.append(xmlStreamReader.getText().trim());
    }

    private static void endElement(XMLStreamReader xmlStreamReader, StringBuilder currentElementValue,
                                   Course course, List<OnlineCourse> onlineCourses, List<OnsiteCourse> onsiteCourses,
                                   List<Attendance> attendances, OnlineCourse currentOnlineCourse,
                                   OnsiteCourse currentOnsiteCourse, Attendance currentAttendance) {
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
            handleEndElement(endElementName, currentElementValue, course, onlineCourses, onsiteCourses, attendances,
                    currentOnlineCourse, currentOnsiteCourse, currentAttendance);
        }
    }

    private static void handleEndElement(String endElementName, StringBuilder currentElementValue,
                                         Course course, List<OnlineCourse> onlineCourses, List<OnsiteCourse> onsiteCourses,
                                         List<Attendance> attendances, OnlineCourse currentOnlineCourse,
                                         OnsiteCourse currentOnsiteCourse, Attendance currentAttendance) {
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
                currentOnsiteCourse.setDate(LocalDate.parse(currentElementValue.toString()));
                break;
            case "status":
                currentAttendance.setStatus(currentElementValue.toString());
                break;
        }
    }
}

