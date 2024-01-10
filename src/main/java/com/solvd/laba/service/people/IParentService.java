package com.solvd.laba.service.people;

import com.solvd.laba.domain.people.Parent;
import com.solvd.laba.domain.people.Student;

import java.util.List;
import java.util.Set;

public interface IParentService {
    void create(Parent object);
    void update(Parent parent);
    void delete(Long id);
    Set<Student> getStudentsByParentId(long parentId);

    List<Parent> getAll();
}
