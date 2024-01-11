package com.solvd.laba.persistence.courses.impl.mybatis;

import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.courses.info.OnsiteCourse;
import com.solvd.laba.persistence.courses.IOnsiteCourseDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OnsiteCourseMyBatisImpl implements IOnsiteCourseDAO {

    @Override
    public OnsiteCourse getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IOnsiteCourseDAO onsiteCourseDAO = sqlSession.getMapper(IOnsiteCourseDAO.class);
            return onsiteCourseDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(OnsiteCourse onsiteCourse, Long onsiteCourseId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IOnsiteCourseDAO onsiteCourseDAO = sqlSession.getMapper(IOnsiteCourseDAO.class);
            onsiteCourseDAO.create(onsiteCourse, onsiteCourseId);
        }
    }

    @Override
    public List<OnsiteCourse> getAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IOnsiteCourseDAO onsiteCourseDAO = sqlSession.getMapper(IOnsiteCourseDAO.class);
            return onsiteCourseDAO.getAll();
        }
    }

    @Override
    public void update(OnsiteCourse onsiteCourse) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IOnsiteCourseDAO onsiteCourseDAO = sqlSession.getMapper(IOnsiteCourseDAO.class);
            onsiteCourseDAO.update(onsiteCourse);
        }
    }

    @Override
    public void delete(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IOnsiteCourseDAO onsiteCourseDAO = sqlSession.getMapper(IOnsiteCourseDAO.class);
            onsiteCourseDAO.delete(id);
        }
    }
}

