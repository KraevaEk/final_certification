package ru.inno.db;

import ru.inno.model.EmployeeEntity;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeRepository {

    List<EmployeeEntity> getCompanyId(int companyId) throws SQLException;
    EmployeeEntity getById(int id) throws SQLException;
    public int deleteId(int id) throws SQLException;
    public int updateId(int id) throws SQLException;

}
