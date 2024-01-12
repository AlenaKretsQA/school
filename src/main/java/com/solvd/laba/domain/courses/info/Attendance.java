package com.solvd.laba.domain.courses.info;

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


public class Attendance {
    private long id;
    private String status;
}
