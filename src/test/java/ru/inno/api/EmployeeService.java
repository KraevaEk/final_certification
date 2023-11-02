package ru.inno.api;

import ru.inno.model.CreateEmployeeResponse;
import ru.inno.model.EditEmployeeResponse;
import ru.inno.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeService extends Authorizable {

    List<Employee> getCompanyId(int CompanyId) throws IOException;

    Employee getById(int id) throws IOException;

    ApiResponse<EditEmployeeResponse> edit(String lastName, String email, String url, String phone, boolean isActive, int lastId) throws IOException;

    ApiResponse<CreateEmployeeResponse> create(String firstName, String lastName, String middleName, int companyId, String phone) throws IOException;

}
