package ru.inno.test;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.inno.api.*;
import ru.inno.db.CompanyRepository;
import ru.inno.db.EmployeeRepository;
import ru.inno.ext.*;
import ru.inno.model.*;
import io.qameta.allure.Step;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({EmployeeServiceResolver.class, EmployeeRepositoryResolver.class, CompanyRepositoryResolver.class, CompanyServiceResolver.class})
@DisplayName("Итоговая аттестация")
public class FinalCertificationTest {

    @ParameterizedTest(name = "= {0}")
    @DisplayName("Проверить, что список компаний фильтруется по параметру active")
    @ValueSource(booleans = {true, false})
    @Description("Content-Length приходит в запросе /company Получить список компаний, только с параметром false")
    public void shouldCompanyActiveTest(boolean isActive, CompanyService service, CompanyRepository companyRepository) throws IOException, SQLException {
        int companyId = createCompany(companyRepository);
        int companyId2 = createCompany(companyRepository);
        companyRepository.updateActive(companyId);
        List<Company> company = service.getAll(isActive);
        List<CompanyEntity> companyEntity = companyRepository.getAllIsActive(isActive);
        companyRepository.deleteById(companyId);
        companyRepository.deleteById(companyId2);
        step("Проверить список компаний на соответсвие установленному значению фильтра active", () -> {
            for (Company list : company) {
                assertEquals(isActive, list.isActive());
            }
        });
        step("Количество компаний в списке сервиса: " + company.size() + "; Количество компаний в списке БД: " + companyEntity.size(), () ->
                assertEquals(company.size(), companyEntity.size()));
    }

    @Test
    @DisplayName("Проверить создание сотрудника в несуществующей компании")
    public void shouldNotCreateEmployeeCompany500(
            @Authorized(username = "donatello", password = "does-machines")
            EmployeeService service, CompanyRepository companyRepository) throws IOException, SQLException {
        int companyId = createCompany(companyRepository);
        companyRepository.deleteById(companyId);
        Faker fakerEmployee = new Faker(new Locale("ru"));
        String firstName = fakerEmployee.name().firstName();
        String lastName = fakerEmployee.name().lastName();
        String middleName = fakerEmployee.name().firstName();
        String phone = "+7(927)1237856";
        ApiResponse<CreateEmployeeResponse> response = service.create(firstName, lastName, middleName, companyId, phone);
        step("При создании сотрудника возвращается ошибка " + " " + response.getStatusCode() + " " + response.getApiError().message(), () ->
                assertEquals(500, response.getStatusCode()));
        assertEquals("Internal server error", response.getApiError().message());
    }

    @Test
    @DisplayName("Проверить, что неактивный сотрудник не отображается в списке")
    @Description("Неактивные сотрудники отображаются в списке, тест провалится")
    public void shouldNoActiveEmployee(
            @Authorized(username = "donatello", password = "does-machines")
            EmployeeService service, EmployeeRepository repository, CompanyRepository companyRepository) throws IOException, SQLException {
        int companyId = createCompany(companyRepository);
        int newIdEmployee = createEmployee(service, companyId);
        int newIdEmployee2 = createEmployee(service, companyId);
        repository.updateActive(newIdEmployee);
        List<Employee> list = service.getCompanyId(companyId);
        repository.deleteId(newIdEmployee);
        repository.deleteId(newIdEmployee2);
        companyRepository.deleteById(companyId);
        step("Проверить отображение неактивных сотрудников в списке", () -> {
            for (Employee employee : list) {
                assertTrue(employee.isActive());
            }
        });
    }

    @Test
    @DisplayName("Проверить, что у удаленной компании проставляется в БД поле deletedAt")
    public void shouldCompanyDeletedAt(
            @Authorized(username = "donatello", password = "does-machines")
            CompanyService service, CompanyRepository companyRepository) throws IOException, SQLException, InterruptedException {
        int companyId = createCompany(companyRepository);
        service.deleteById(companyId);
        Thread.sleep(1000);
        CompanyEntity companyEntity = companyRepository.getById(companyId);
        companyRepository.deleteById(companyId);
        step("Проверить, что у удаленной компании проставлена дата удаления " + dateConversion(companyEntity.getDeletedAt()), () -> {
            assertNotNull(companyEntity.getDeletedAt());
        });
    }

    @Step("Создать компанию в БД")
    public int createCompany (
            CompanyRepository companyRepository) throws SQLException {
        Faker fakerCompany = new Faker(new Locale("ru"));
        String nameCompany = fakerCompany.company().name();
        String descriptionCompany = fakerCompany.address().fullAddress();
        return companyRepository.create(nameCompany, descriptionCompany);
    }

    @Step("Создать сотрудника в сервисе")
    public int createEmployee (
            @Authorized(username = "donatello", password = "does-machines")
            EmployeeService service, int companyId) throws IOException {
        Faker fakerEmployee = new Faker(new Locale("ru"));
        String firstName = fakerEmployee.name().firstName();
        String lastName = fakerEmployee.name().lastName();
        String middleName = fakerEmployee.name().firstName();
        String phone = "+7(927)1237856";
        ApiResponse<CreateEmployeeResponse> response = service.create(firstName, lastName, middleName, companyId, phone);
        return response.getBody().getId();
    }

    public String dateConversion (Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return dateFormat.format(timestamp.getTime());
    }
}
