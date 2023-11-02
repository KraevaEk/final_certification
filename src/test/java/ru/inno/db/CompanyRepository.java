package ru.inno.db;

import ru.inno.model.CompanyEntity;

import java.sql.SQLException;
import java.util.List;

public interface CompanyRepository {

    int create(String name, String description) throws SQLException;

    void deleteById(int id) throws SQLException;

    List<CompanyEntity> isActive(boolean active) throws SQLException;
}