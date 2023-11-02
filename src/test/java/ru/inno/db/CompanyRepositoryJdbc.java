package ru.inno.db;

import ru.inno.model.Company;
import ru.inno.model.CompanyEntity;
import ru.inno.model.EmployeeEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CompanyRepositoryJdbc implements CompanyRepository {
    private final static String INSERT = "insert into company(name, description) values(?, ?)";
    private final static String DELETE = "delete from company where id = ?";
    private final static String IS_ACTIVE = "select * from company where deleted_at is null and is_active = ?";

    private Connection connection;

    public CompanyRepositoryJdbc(Connection connection) {
        this.connection = connection;
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

    @Override
    public void deleteById(int id) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE, Statement.RETURN_GENERATED_KEYS);
    preparedStatement.setInt(1, id);
    preparedStatement.executeUpdate();
    }

    @Override
    public List<CompanyEntity> isActive(boolean active) throws SQLException {
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
}