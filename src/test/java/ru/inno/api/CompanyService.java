package ru.inno.api;

import ru.inno.model.Company;
import ru.inno.model.CreateCompanyResponse;

import java.io.IOException;
import java.util.List;

public interface CompanyService extends Authorizable {

    List<Company> getAll() throws IOException;

    List<Company> getAll(boolean isActive) throws IOException;

    Company getById(int id) throws IOException;

    ApiResponse<CreateCompanyResponse> create(String name) throws IOException;

    ApiResponseCompany<CreateCompanyResponse> create(String name, String description) throws IOException;

    void deleteById(int id);

    Company edit(int id, String newName);

    Company edit(int id, String newName, String newDescription);

    Company changeStatus(int id, boolean isActive);

}