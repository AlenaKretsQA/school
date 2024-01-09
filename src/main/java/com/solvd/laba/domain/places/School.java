package com.solvd.laba.domain.places;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.security.Timestamp;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class School {
    private long id;
    private String schoolName;
    private String location;
    private Timestamp establishedDate;
    private Set<Department> departments;
}
