package com.solvd.laba.parsers.jaxb;

import com.solvd.laba.parsers.model.Course;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JaxbMain {
    public static void main(String [] args){

        File xmlFile = new File("src/main/resources/parsers/xml/course.xml");
        try {
            JAXBContext context = JAXBContext.newInstance(Course.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Course course = (Course) unmarshaller.unmarshal(xmlFile);
            System.out.println(course);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}
