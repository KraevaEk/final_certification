package ru.inno.db;

import ru.inno.model.CompanyEntity;

import java.sql.SQLException;
import java.util.List;

public interface CompanyRepository {

    int create(String name, String description) throws SQLException;
    void deleteById(int id) throws SQLException;
    List<CompanyEntity> getAllIsActive(boolean active) throws SQLException;
    public CompanyEntity getById(int id) throws SQLException;
    public int updateActive(int id) throws SQLException;
    public List<CompanyEntity> getAll() throws SQLException;
}