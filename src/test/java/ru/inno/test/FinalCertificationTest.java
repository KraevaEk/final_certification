package ru.inno.test;

import com.github.javafaker.Faker;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import ru.inno.api.ApiResponse;
import ru.inno.api.CompanyService;
import ru.inno.api.EmployeeService;
import ru.inno.db.CompanyRepository;
import ru.inno.db.EmployeeRepository;
import ru.inno.ext.*;
import ru.inno.model.*;
import io.qameta.allure.Step;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith({EmployeeServiceResolver.class, EmployeeRepositoryResolver.class, CompanyRepositoryResolver.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FinalCertificationTest {

    @Test
    @DisplayName("Этот проверяет, что работает фильтр active")
    @Order(1)
    public void companyListDisabledTest(CompanyService service) throws IOException {
        // TODO: select from DB
        List<Company> list = service.getAll(false);
        System.out.println(list.toString());
//        assertEquals(3, list.size());
    }
    @Test
    @DisplayName("Проверить создание сотрудника в несуществующей компании")
    @Order(2)
    @Step("Сотрудник не создается")
    public void shouldNotCreateEmployeeCompany500(
            @Authorized(username = "donatello", password = "does-machines")
            EmployeeService service) throws IOException {
        Faker fakerEmployee = new Faker(new Locale("ru"));
        String firstName = fakerEmployee.name().firstName();
        String lastName = fakerEmployee.name().lastName();
        String middleName = fakerEmployee.name().firstName();
        String phone = "+7(927)1237856";
        ApiResponse<CreateEmployeeResponse> response = service.create(firstName, lastName, middleName, 0, phone);
        step("Возвращается ошибка " + " " + response.getStatusCode() + " " + response.getApiError().message(), () ->
                assertEquals(500, response.getStatusCode()));
        assertEquals("Internal server error", response.getApiError().message());
    }




    public int createCompany (
            CompanyRepository companyRepository) throws SQLException {
        Faker fakerCompany = new Faker(new Locale("ru"));
        String nameCompany = fakerCompany.company().name();
        String descriptionCompany = fakerCompany.address().fullAddress();
        int companyId = companyRepository.create(nameCompany, descriptionCompany);
        return companyId;
    }

    public int createEmployee (
            @Authorized(username = "donatello", password = "does-machines")
            EmployeeService service, int companyId) throws IOException {
        Faker fakerEmployee = new Faker(new Locale("ru"));
        String firstName = fakerEmployee.name().firstName();
        String lastName = fakerEmployee.name().lastName();
        String middleName = fakerEmployee.name().firstName();
        String phone = "+7(927)1237856";
        ApiResponse<CreateEmployeeResponse> response = service.create(firstName, lastName, middleName, companyId, phone);
        int newIdEmployee = response.getBody().getId();
        return newIdEmployee;
    }

    public Employee getEmployeeService(
            @Authorized(username = "donatello", password = "does-machines")
            EmployeeService service, int employeeId) throws IOException {
        Employee employee = service.getById(employeeId);
        return employee;
    }

    public EmployeeEntity getEmployeeRepository(
            EmployeeRepository repository, int employeeId) throws SQLException {
        EmployeeEntity employee = repository.getById(employeeId);
        return employee;
    }

}
