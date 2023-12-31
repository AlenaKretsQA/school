package com.solvd.laba.persistence.people.impl;


import com.solvd.laba.domain.people.Person;
import com.solvd.laba.domain.people.Teacher;
import com.solvd.laba.persistence.people.IPersonDAO;

import java.util.List;

public abstract class PersonDAO<T extends Person> implements IPersonDAO<T> {
    public abstract void create(Person person);

    public abstract List<Teacher> getAll();

    public abstract void delete(Long id);
}
