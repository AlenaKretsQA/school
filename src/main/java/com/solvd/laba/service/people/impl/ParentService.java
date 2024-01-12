package com.solvd.laba.service.people.impl;

import com.solvd.laba.domain.people.Parent;
import com.solvd.laba.domain.people.Student;
import com.solvd.laba.persistence.people.IParentDAO;
import com.solvd.laba.service.people.IParentService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Set;

@AllArgsConstructor

public class ParentService implements IParentService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    IParentDAO iParentDAO;

    @Override
    public void create(Parent object) {
        iParentDAO.create(new Parent());
    }

    @Override
    public void update(Parent parent) {
         iParentDAO.update(parent);
    }

    @Override
    public void delete(Long id) {
iParentDAO.delete(id);
    }

    @Override
    public Set<Student> getStudentsByParentId(long parentId) {
        return iParentDAO.getStudentsByParentId(parentId);
    }

    @Override
    public List<Parent> getAll() {
        return iParentDAO.getAll();
    }
}
