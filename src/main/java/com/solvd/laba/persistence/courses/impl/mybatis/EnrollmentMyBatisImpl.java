package com.solvd.laba.persistence.courses.impl.mybatis;

import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.courses.info.Enrollment;
import com.solvd.laba.persistence.courses.IEnrollmentDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EnrollmentMyBatisImpl implements IEnrollmentDAO {

    @Override
    public void create(Enrollment enrollment, Long enrollmentId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IEnrollmentDAO enrollmentDAO = sqlSession.getMapper(IEnrollmentDAO.class);
            enrollmentDAO.create(enrollment, enrollmentId);
        }
    }

    @Override
    public List<Enrollment> getAllEnrollmentsByStudentId(Long studentId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IEnrollmentDAO enrollmentDAO = sqlSession.getMapper(IEnrollmentDAO.class);
            return enrollmentDAO.getAllEnrollmentsByStudentId(studentId);
        }
    }

    @Override
    public void update(Enrollment enrollment) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IEnrollmentDAO enrollmentDAO = sqlSession.getMapper(IEnrollmentDAO.class);
            enrollmentDAO.update(enrollment);
        }
    }

    @Override
    public void delete(long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IEnrollmentDAO enrollmentDAO = sqlSession.getMapper(IEnrollmentDAO.class);
            enrollmentDAO.delete(id);
        }
    }

    @Override
    public Enrollment getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IEnrollmentDAO enrollmentDAO = sqlSession.getMapper(IEnrollmentDAO.class);
            return enrollmentDAO.getById(id);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

