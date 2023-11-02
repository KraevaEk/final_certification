package ru.inno.ext;

import org.junit.jupiter.api.extension.*;
import ru.inno.db.EmployeeRepository;
import ru.inno.db.EmployeeRepositoryJdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class EmployeeRepositoryResolver implements ParameterResolver, BeforeAllCallback, AfterAllCallback {
    private Connection connection = null;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(EmployeeRepository.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return new EmployeeRepositoryJdbc(connection);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        System.out.println("disconnecting");
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        System.out.println("connecting");
        String connectionString = "jdbc:postgresql://dpg-cj94hf0eba7s73bdki80-a.oregon-postgres.render.com/x_clients_db_r06g";
        String user = "x_clients_db_r06g_user";
        String pass = "0R1RNWXMepS7mrvcKRThRi82GtJ2Ob58";
        connection = DriverManager.getConnection(connectionString, user, pass);
    }
}
