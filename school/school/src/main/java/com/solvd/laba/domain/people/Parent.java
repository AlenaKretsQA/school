package com.solvd.laba.domain.people;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Parent extends Person {
    private Set<Student> students;
    public Parent(long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }
}
