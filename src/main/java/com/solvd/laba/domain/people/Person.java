package com.solvd.laba.domain.people;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class Person {

    private long id;
    private String firstName;
    private String lastName;
}
