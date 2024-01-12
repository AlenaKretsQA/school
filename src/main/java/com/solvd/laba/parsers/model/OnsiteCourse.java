package com.solvd.laba.parsers.model;


import com.solvd.laba.domain.courses.info.Attendance;
import com.solvd.laba.parsers.jaxb.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor

@XmlAccessorType(XmlAccessType.FIELD)
public class OnsiteCourse {
    private long id;
    private int roomNumber;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;
    private LocalDate date;
    private List<Attendance> attendances;
}
