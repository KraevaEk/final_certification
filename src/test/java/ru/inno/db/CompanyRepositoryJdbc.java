package ru.inno.db;

import io.qameta.allure.Step;
import ru.inno.model.CompanyEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepositoryJdbc implements CompanyRepository {
    private final static String INSERT = "insert into company(name, description) values(?, ?)";
    private final static String DELETE = "delete from company where id = ?";
    private final static String IS_ACTIVE = "select * from company where deleted_at is null and is_active = ?";
    private final static String ID_COMPANY = "select * from company where id = ?";
    private final static String UPDATE_COMPANY = "update company set is_active = false where id = ?";
    private final static String SELECT = "select * from company";

    private Connection connection;

    public CompanyRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }


    @Step("Получить компанию по id в БД")
    @Override
    public CompanyEntity getById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ID_COMPANY);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        CompanyEntity entity = new CompanyEntity();
        entity.setId(resultSet.getInt("id"));
        entity.setActive(resultSet.getBoolean("is_active"));
        entity.setCreateDateTime(resultSet.getTimestamp("create_timestamp"));
        entity.setLastChangedDateTime(resultSet.getTimestamp("change_timestamp"));
        entity.setName(resultSet.getString("name"));
        entity.setDescription(resultSet.getString("description"));
        entity.setDeletedAt(resultSet.getTimestamp("deleted_at"));
        return entity;
    }
    @Override
    public int create(String name, String description) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, description);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getInt(1);
    }

    @Step("Удалить компанию по id в БД")
    @Override
    public void deleteById(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE, Statement.RETURN_GENERATED_KEYS);
    preparedStatement.setInt(1, id);
    preparedStatement.executeUpdate();
    }

    @Step("Получить список компаний в зависимости от активности в БД")
    @Override
    public List<CompanyEntity> getAllIsActive(boolean active) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(IS_ACTIVE, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setBoolean(1, active);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<CompanyEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            CompanyEntity entity = new CompanyEntity();
            entity.setId(resultSet.getInt("id"));
            entity.setActive(resultSet.getBoolean("is_active"));
            entity.setCreateDateTime(resultSet.getTimestamp("create_timestamp"));
            entity.setLastChangedDateTime(resultSet.getTimestamp("change_timestamp"));
            entity.setName(resultSet.getString("name"));
            entity.setDescription(resultSet.getString("description"));
            entity.setDeletedAt(resultSet.getTimestamp("deleted_at"));
            list.add(entity);
        }
        return list;
    }

    @Step("Изменить активность компании по id в БД")
    public int updateActive(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMPANY, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getInt(1);
    }

    @Step("Получить все компании в БД")
    public List<CompanyEntity> getAll() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT);
        List<CompanyEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            CompanyEntity entity = new CompanyEntity();
            entity.setId(resultSet.getInt("id"));
            entity.setActive(resultSet.getBoolean("is_active"));
            entity.setCreateDateTime(resultSet.getTimestamp("create_timestamp"));
            entity.setLastChangedDateTime(resultSet.getTimestamp("change_timestamp"));
            entity.setName(resultSet.getString("name"));
            entity.setDescription(resultSet.getString("description"));
            entity.setDeletedAt(resultSet.getTimestamp("deleted_at"));
            list.add(entity);
        }
        return list;
    }
}