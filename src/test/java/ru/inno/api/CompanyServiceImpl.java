package ru.inno.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import ru.inno.model.*;

import java.io.IOException;
import java.util.List;


public class CompanyServiceImpl implements CompanyService {
    public static final MediaType APPLICATION_JSON = MediaType.parse("application/json; charset=UTF-8");
    private static final String PATH = "company";
    private static final String DELETE = "delete";
    private final String BASE_PATH;
    private final OkHttpClient client;
    private final ObjectMapper mapper;
    private String token;

    public CompanyServiceImpl(OkHttpClient client, String url) {
        this.client = client;
        this.BASE_PATH = url;
        this.mapper = new ObjectMapper();
    }

    @Step("Получить список всех компаний в сервисе")
    @Override
    public List<Company> getAll() throws IOException {
        HttpUrl url = getUrl().build();
        return getCompanies(url);
    }

    @Step("Получить список компаний в зависимости от активности в сервисе")
    @Override
    public List<Company> getAll(boolean isActive) throws IOException {
        HttpUrl url = getUrl()
                .addQueryParameter("active", Boolean.toString(isActive))
                .build();
        return getCompanies(url);
    }

    @Step("Получить компанию по id в сервисе")
    @Override
    public Company getById(int id) throws IOException {
        HttpUrl url = getUrl()
                .addPathSegment(Integer.toString(id))
                .build();
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return mapper.readValue(response.body().string(), Company.class);
    }


    @Override
    public ApiResponseCompany<CreateCompanyResponse> create(String name, String description) throws IOException {
        HttpUrl url = getUrl().build();
        CreateCompanyRequest body1 = new CreateCompanyRequest(name, description);
        RequestBody jsonBody1 = RequestBody.create(mapper.writeValueAsString(body1), APPLICATION_JSON);
        Request.Builder request1 = new Request.Builder().post(jsonBody1).url(url);

        if (token != null) {
            request1.addHeader("x-client-token", token);
        }

        try (Response response1 = this.client.newCall(request1.build()).execute()) {
            if (response1.code() >= 400) {
            ApiError body = mapper.readValue(response1.body().string(), ApiError.class);
            return new ApiResponseCompany<>(response1.headers().toMultimap(), response1.code(), null, body);
            } else {
            CreateCompanyResponse body = mapper.readValue(response1.body().string(), CreateCompanyResponse.class);
            return new ApiResponseCompany<>(response1.headers().toMultimap(), response1.code(), body, null);
            }
        }
    }

    @Step("Удалить компанию по id в сервисе")
    @Override
    public void deleteById(int id) throws IOException {
        HttpUrl url = getUrl().addPathSegment(DELETE).addPathSegment(Integer.toString(id)).build();
        Request.Builder request = new Request.Builder().get().url(url);
        if (token != null) {
            request.addHeader("x-client-token", token);
        }
        this.client.newCall(request.build()).execute();

    }

    private List<Company> getCompanies(HttpUrl url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), new TypeReference<>() {});
    }

    @NotNull
    private HttpUrl.Builder getUrl() {
        return HttpUrl.parse(BASE_PATH).newBuilder().addPathSegment(PATH);
    }

    public void setToken(String token) {
        this.token = token;
    }
}