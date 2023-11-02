package ru.inno.ext;

import org.junit.jupiter.api.extension.*;
import ru.inno.db.CompanyRepository;
import ru.inno.db.CompanyRepositoryJdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class CompanyRepositoryResolver implements ParameterResolver, BeforeAllCallback, AfterAllCallback {
    private Connection connection = null;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CompanyRepository.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return new CompanyRepositoryJdbc(connection);
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

        String connectionString = PropertyProvider.getInstance().getProps().getProperty("connection");
        String user = PropertyProvider.getInstance().getProps().getProperty("user");
        String pass = PropertyProvider.getInstance().getProps().getProperty("pass");
        connection = DriverManager.getConnection(connectionString, user, pass);
    }
}