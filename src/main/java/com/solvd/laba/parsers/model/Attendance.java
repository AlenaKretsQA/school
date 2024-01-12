package com.solvd.laba.parsers.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.security.Timestamp;
@Getter
@Setter
@ToString
@NoArgsConstructor

@XmlAccessorType(XmlAccessType.FIELD)
public class Attendance {
    private long id;
    private String status;
}
