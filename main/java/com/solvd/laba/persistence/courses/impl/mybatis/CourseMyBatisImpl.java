package com.solvd.laba.persistence.courses.impl.mybatis;

import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.courses.info.Course;
import com.solvd.laba.persistence.courses.ICourseDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CourseMyBatisImpl implements ICourseDAO {

    @Override
    public Course getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ICourseDAO courseDAO = sqlSession.getMapper(ICourseDAO.class);
            return courseDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course> getAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ICourseDAO courseDAO = sqlSession.getMapper(ICourseDAO.class);
            return courseDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Course course) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ICourseDAO courseDAO = sqlSession.getMapper(ICourseDAO.class);
            courseDAO.create(course);
        }
    }

    @Override
    public void update(Course course) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ICourseDAO courseDAO = sqlSession.getMapper(ICourseDAO.class);
            courseDAO.update(course);
        }
    }

    @Override
    public void delete(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            ICourseDAO courseDAO = sqlSession.getMapper(ICourseDAO.class);
            courseDAO.delete(id);
        }
    }
}
