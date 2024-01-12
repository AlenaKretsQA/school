package com.solvd.laba.persistence.courses.impl.mybatis;
import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.courses.info.OnlineCourse;
import com.solvd.laba.persistence.courses.IOnlineCourseDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class OnlineCourseMyBatisImpl implements IOnlineCourseDAO {

    @Override
    public OnlineCourse getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IOnlineCourseDAO onlineCourseDAO = sqlSession.getMapper(IOnlineCourseDAO.class);
            return onlineCourseDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(OnlineCourse onlineCourse, Long onlineCourseId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IOnlineCourseDAO onlineCourseDAO = sqlSession.getMapper(IOnlineCourseDAO.class);
            onlineCourseDAO.create(onlineCourse, onlineCourseId);
        }
    }

    @Override
    public List<OnlineCourse> getAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IOnlineCourseDAO onlineCourseDAO = sqlSession.getMapper(IOnlineCourseDAO.class);
            return onlineCourseDAO.getAll();
        }
    }

    @Override
    public void update(OnlineCourse onlineCourse) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IOnlineCourseDAO onlineCourseDAO = sqlSession.getMapper(IOnlineCourseDAO.class);
            onlineCourseDAO.update(onlineCourse);
        }
    }

    @Override
    public void delete(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IOnlineCourseDAO onlineCourseDAO = sqlSession.getMapper(IOnlineCourseDAO.class);
            onlineCourseDAO.delete(id);
        }
    }
}
