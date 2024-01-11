package com.solvd.laba.persistence.people.impl;
import com.solvd.laba.domain.people.Person;
import com.solvd.laba.domain.people.Teacher;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.people.ITeacherDAO;
import com.solvd.laba.persistence.places.IDepartmentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class TeacherDAO implements ITeacherDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_QUERY = "INSERT INTO teachers (id, first_name, last_name, hire_date) VALUES (?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM teachers WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM teachers";
    private static final String UPDATE_QUERY = "UPDATE teachers SET first_name = ?, last_name = ?, hire_date = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM teachers WHERE id = ?";


   static IDepartmentDAO iDepartmentDAO;
   public static List<Teacher> mapRow(ResultSet resultSet, List<Teacher> teachers) throws SQLException {

        if (teachers == null) {
            teachers = new ArrayList<>();
        }

        long id = resultSet.getLong("id");

        if (id != 0) {
            Teacher teacher = findById(id, teachers);
            teacher.setFirstName(resultSet.getString("first_name"));
            teacher.setLastName(resultSet.getString("last_name"));
            teacher.setHireDate(resultSet.getDate("hire_date"));
          //  teacher.setDepartments(iDepartmentDAO.getAllDepartmentsByTeacherId(id));
            teachers.add(teacher);
        }

        return teachers;
    }
    private static Teacher findById(Long id, List<Teacher> teachers) {
        return teachers.stream()
                .filter(teacher -> teacher.getId()==(id))
                .findFirst()
                .orElseGet(() -> {
                    Teacher newTeacher = new Teacher();
                    newTeacher.setId(id);
                    teachers.add(newTeacher);
                    return newTeacher;
                });
    }
    @Override
    public void create(Teacher teacher) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, String.valueOf(teacher.getHireDate()));

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    teacher.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
            }

            LOGGER.info("Customer created: {}", teacher);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public Teacher getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Teacher> teachers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {

                mapRow(result, teachers);

            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return teachers.get(0);
    }

    @Override
    public void update(Person object) {

    }


    @Override
    public List<Teacher> getAllTeachers() {

        Connection connection = CONNECTION_POOL.getConnection();
        List<Teacher> teachers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {

            ResultSet result = preparedStatement.executeQuery();

           while (result.next()) {
                teachers.addAll(teachers);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return teachers;
    }
    @Override
    public void update(Teacher teacher) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, String.valueOf(teacher.getId()));
            preparedStatement.setString(2, teacher.getFirstName());
            preparedStatement.setString(3, teacher.getLastName());
            preparedStatement.setLong(4, Long.parseLong(String.valueOf(teacher.getHireDate())));

            preparedStatement.executeUpdate();

            LOGGER.info("Teacher updated: {}", teacher);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
    @Override
    public void addTeacher(Teacher teacher) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setLong(1, teacher.getId());
            preparedStatement.setString(2, teacher.getFirstName());
            preparedStatement.setString(3, teacher.getLastName());
            preparedStatement.setDate(4, Date.valueOf(teacher.getHireDate().toLocalDate()));

            preparedStatement.executeUpdate();

            LOGGER.info("Teacher added: {}", teacher);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteTeacher(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Teacher deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    }


