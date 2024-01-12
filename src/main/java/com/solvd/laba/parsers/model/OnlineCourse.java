package com.solvd.laba.parsers.model;


import com.solvd.laba.domain.courses.info.Grade;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode

@XmlAccessorType(XmlAccessType.FIELD)
public class OnlineCourse {
    private long id;
    private String url;
    private Grade gradeId;
}

