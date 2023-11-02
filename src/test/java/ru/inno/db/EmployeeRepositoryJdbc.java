package ru.inno.db;

import io.qameta.allure.Step;
import ru.inno.model.EmployeeEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryJdbc implements EmployeeRepository{
    private final static String SELECT_COMPANY_ID = "select * from employee where company_id = ?";
    private final static String SELECT_LAST = "select * from employee order by id desc limit 1";
    private final static String SELECT_EMPLOYEE = "select * from employee where id = ?";
    private final static String DELETE_EMPLOYEE = "delete from employee where id = ?";
    private final static String UPDATE_EMPLOYEE = "update employee set is_active = false where id = ?";
    private Connection connection;

    public EmployeeRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Step("Получить сотрудника по id компании в БД")
    @Override
    public List<EmployeeEntity> getCompanyId(int companyId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANY_ID);
        preparedStatement.setInt(1, companyId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<EmployeeEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            EmployeeEntity entity = new EmployeeEntity();
            entity.setId(resultSet.getInt("id"));
            entity.setActive(resultSet.getBoolean("is_active"));
            entity.setFirstName(resultSet.getString("first_name"));
            entity.setLastName(resultSet.getString("last_name"));
            entity.setMiddleName(resultSet.getString("middle_name"));
            entity.setCompanyId(resultSet.getInt("company_id"));
            entity.setCreateDateTime(resultSet.getTimestamp("create_timestamp"));
            entity.setLastChangedDateTime(resultSet.getTimestamp("change_timestamp"));
            entity.setPhone(resultSet.getString("phone"));
            entity.setEmail(resultSet.getString("email"));
            entity.setAvatarUrl(resultSet.getString("avatar_url"));
            entity.setBirthdate(resultSet.getDate("birthdate"));
            list.add(entity);
        }
        return list;
    }

    @Step("Получить сотрудника по id в БД")
    @Override
    public EmployeeEntity getById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(resultSet.getInt("id"));
        entity.setActive(resultSet.getBoolean("is_active"));
        entity.setFirstName(resultSet.getString("first_name"));
        entity.setLastName(resultSet.getString("last_name"));
        entity.setMiddleName(resultSet.getString("middle_name"));
        entity.setCompanyId(resultSet.getInt("company_id"));
        entity.setCreateDateTime(resultSet.getTimestamp("create_timestamp"));
        entity.setLastChangedDateTime(resultSet.getTimestamp("change_timestamp"));
        entity.setPhone(resultSet.getString("phone"));
        entity.setEmail(resultSet.getString("email"));
        entity.setAvatarUrl(resultSet.getString("avatar_url"));
        entity.setBirthdate(resultSet.getDate("birthdate"));
        return entity;
    }


    public EmployeeEntity getLast() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT_LAST);
        resultSet.next();
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(resultSet.getInt("id"));
        entity.setActive(resultSet.getBoolean("is_active"));
        entity.setFirstName(resultSet.getString("first_name"));
        entity.setLastName(resultSet.getString("last_name"));
        entity.setMiddleName(resultSet.getString("middle_name"));
        entity.setCompanyId(resultSet.getInt("company_id"));
        entity.setCreateDateTime(resultSet.getTimestamp("create_timestamp"));
        entity.setLastChangedDateTime(resultSet.getTimestamp("change_timestamp"));
        entity.setPhone(resultSet.getString("phone"));
        entity.setEmail(resultSet.getString("email"));
        entity.setAvatarUrl(resultSet.getString("avatar_url"));
        entity.setBirthdate(resultSet.getDate("birthdate"));
        return entity;
    }

    public int getLastCompanyId() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT_LAST);
        resultSet.next();
        int companyId = resultSet.getInt("company_id");
        return companyId;
    }

    public int getLastEmployeeId() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT_LAST);
        resultSet.next();
        int employeeId = resultSet.getInt("id");
        return employeeId;
    }

    @Step("Удалить сотрудника по id в БД")
    public int deleteId(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getInt(1);
    }

    @Step("Изменить активность сотрудника по id в БД")
    public int updateActive(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getInt(1);
    }
}
