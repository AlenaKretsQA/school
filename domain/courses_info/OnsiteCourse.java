package com.solvd.laba.domain.courses_info;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.security.Timestamp;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OnsiteCourse {
    private long id;
    private int roomNumber;
    private Timestamp date;
    private List<Attendance> attendances;
}
