package com.solvd.laba.persistence;

import com.solvd.laba.domain.courses.info.OnlineCourse;

import java.util.List;

public interface CommonDAO<T> {

    T getById(Long id);

    void update(T object);
}
