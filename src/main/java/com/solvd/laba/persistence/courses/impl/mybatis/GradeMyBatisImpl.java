package com.solvd.laba.persistence.courses.impl.mybatis;
import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.courses.info.Grade;
import com.solvd.laba.persistence.courses.IGradeDAO;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GradeMyBatisImpl implements IGradeDAO {

    @Override
    public Grade getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IGradeDAO gradeDAO = sqlSession.getMapper(IGradeDAO.class);
            return gradeDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Grade grade, Long gradeId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IGradeDAO gradeDAO = sqlSession.getMapper(IGradeDAO.class);
            gradeDAO.create(grade, gradeId);
        }
    }

    @Override
    public List<Grade> getAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IGradeDAO gradeDAO = sqlSession.getMapper(IGradeDAO.class);
            return gradeDAO.getAll();
        }
    }

    @Override
    public void update(Grade grade) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IGradeDAO gradeDAO = sqlSession.getMapper(IGradeDAO.class);
            gradeDAO.update(grade);
        }
    }

    @Override
    public void delete(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IGradeDAO gradeDAO = sqlSession.getMapper(IGradeDAO.class);
            gradeDAO.delete(id);
        }
    }
}

