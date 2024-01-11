package com.solvd.laba.domain.courses.info;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class OnlineCourse {
    private long id;
    private String url;
    private Grade gradeId;
}
