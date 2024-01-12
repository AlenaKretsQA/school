package com.solvd.laba.persistence.places.impl;

import com.solvd.laba.domain.people.Student;
import com.solvd.laba.domain.people.Teacher;
import com.solvd.laba.domain.places.Department;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.places.IDepartmentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepartmentDAO implements IDepartmentDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String ADD_DEPARTMENT_QUERY = "INSERT INTO departments (id, name, school_id) VALUES (?, ?, ?)";
    private static final String GET_DEPARTMENT_BY_ID_QUERY = "SELECT * FROM departments WHERE id = ?";
    private static final String GET_ALL_DEPARTMENTS_QUERY = "SELECT * FROM departments";
    private static final String GET_ALL_TEACHERS_QUERY = "SELECT t.id, t.first_name, t.last_name, t.hire_date FROM teachers t INNER JOIN departments d ON t.department_id = d.id WHERE d.id=?";
    private static final String GET_ALL_DEPARTMENTS_BELONGS_TO_TEACHER_QUERY = "SELECT d.id, d.name, d.school_id FROM departments d INNER JOIN school.teachers_departments td ON d.id = td.department_id WHERE td.teacher_id=?";
    private static final String UPDATE_DEPARTMENT_QUERY = "UPDATE departments SET name = ?, school_id = ? WHERE id = ?";
    private static final String DELETE_DEPARTMENT_QUERY = "DELETE FROM departments WHERE id = ?";

    @Override
    public void addDepartment(Department department) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_DEPARTMENT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setLong(2, department.getSchoolId());

            preparedStatement.executeUpdate();

            LOGGER.info("Department added: {}", department);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Department getDepartmentById(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_DEPARTMENT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return mapRowDepartment(result);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return null;
    }

    @Override
    public List<Department> getAllDepartments() {
        return null;
    }

    @Override
    public void updateDepartment(Department department) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEPARTMENT_QUERY)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setLong(2, department.getSchoolId());
            preparedStatement.setLong(3, department.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Department updated: {}", department);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteDepartment(long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPARTMENT_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Department deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Set<Teacher> getAllTeachersByDepartmentId(long department_id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Set<Teacher> teachers = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TEACHERS_QUERY)) {
            preparedStatement.setLong(1, department_id);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                teachers.add(mapRowTeacher(result));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return teachers;
    }

    @Override
    public Set<Department> getAllDepartmentsByTeacherId(long teacher_id) {  Connection connection = CONNECTION_POOL.getConnection();
        Set<Department> departments = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DEPARTMENTS_BELONGS_TO_TEACHER_QUERY)) {
            preparedStatement.setLong(1, teacher_id);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                departments.add(mapRowDepartment(result));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return departments;

    }

    private Teacher mapRowTeacher(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("id"));
        teacher.setFirstName(resultSet.getString("first_name"));
        teacher.setLastName(resultSet.getString("last_name"));
        teacher.setHireDate(Date.valueOf(String.valueOf(teacher.getHireDate())));
        return teacher;
    }
    private Department mapRowDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getLong("id"));
        department.setName(resultSet.getString("name"));
        department.setSchoolId(resultSet.getLong("school_id0"));

        return department;
    }

    @Override
    public Department getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_DEPARTMENT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return mapRowDepartment(result);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return null;
    }

    @Override
    public void update(Department object) {

    }
}
