package com.solvd.laba.domain.people;

import com.solvd.laba.domain.places.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Teacher extends Person {
    private String firstName;
    private String lastName;
    private Date hireDate;
    private Set<Department> departments;

    public Teacher(long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }
}
