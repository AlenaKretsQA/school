package com.solvd.laba.parsers;

import com.solvd.laba.parsers.model.Course;
import com.solvd.laba.parsers.stax.XMLCourseParser;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class ParsersMain {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    public static void main(String[] args) {

        String xmlFilePath = "src/main/resources/parsers/xml/course.xml";
        String xsdFilePath = "src/main/resources/parsers/xml/course.xsd";

        // 3 main parsers in java: DOM,SAX,StAX

        //StAX read file line by line

        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = schemaFactory.newSchema(new StreamSource(xsdFilePath));

            Validator validator = schema.newValidator();

            validator.validate(new StreamSource(xmlFilePath));

            LOGGER.info("XML validation success");

        } catch (SAXException e) {
            LOGGER.error("Validation failed: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error("Error reading XML file: " + e.getMessage());
            e.printStackTrace();
        }

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try(FileInputStream fis = new FileInputStream(xmlFilePath)){
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(fis);
            while(reader.hasNext()){
                XMLEvent event = reader.nextEvent();
                //event.
            }
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }






        // ***** JAXB *****
        try {
        JAXBContext context = JAXBContext.newInstance(Course.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Course jaxbCourse = (Course) unmarshaller.unmarshal(new File(xmlFilePath));
        System.out.println();
    } catch (
    JAXBException e) {
        throw new RuntimeException(e);
    }

    // ***** Jackson *****

}}