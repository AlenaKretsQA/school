package com.solvd.laba.domain.courses.info;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OnlineCourse {
    private long id;
    private String url;
    private Long gradeId;
}
