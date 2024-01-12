package com.solvd.laba.parsers.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor

@XmlAccessorType(XmlAccessType.FIELD)
public class Grade {
    private long id;
    private String grade;
}
