package com.solvd.laba.domain.courses_info;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Enrollment {
    private long id;
    private Date enrollmentDate;
}
