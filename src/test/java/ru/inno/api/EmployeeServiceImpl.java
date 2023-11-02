package ru.inno.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import ru.inno.model.*;

import java.io.IOException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{
    public static final MediaType APPLICATION_JSON = MediaType.parse("application/json; charset=UTF-8");
    private static final String PATH = "employee";
    private final String BASE_PATH;
    private final OkHttpClient client;
    private final ObjectMapper mapper;
    private String token;

    public EmployeeServiceImpl(OkHttpClient client, String url) {
        this.client = client;
        this.BASE_PATH = url;
        this.mapper = new ObjectMapper();
    }


    @Override
    public List<Employee> getCompanyId(int CompanyId) throws IOException {
        HttpUrl url = getUrl().encodedQuery("company=" + CompanyId).build();
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), new TypeReference<>() {
        });
    }

    @Override
    public Employee getById(int id) throws IOException {
        HttpUrl url = getUrl().addPathSegment(Integer.toString(id)).build();
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), Employee.class);
    }

    @Override
    public ApiResponse<EditEmployeeResponse> edit(String lastName, String email, String urlEmployee, String phone, boolean isActive, int employeeId) throws IOException {
        HttpUrl url = getUrl().addPathSegment(Integer.toString(employeeId)).build();
        EditEmployeeRequest bodyRequest = new EditEmployeeRequest(lastName, email, urlEmployee, phone, isActive);
        RequestBody jsonBody = RequestBody.create(mapper.writeValueAsString(bodyRequest), APPLICATION_JSON);
        Request.Builder request = new Request.Builder().patch(jsonBody).url(url);
        if (token != null){
            request.addHeader("x-client-token", token);
        }

        Response response = this.client.newCall(request.build()).execute();

        if(response.code()>=400){
            ApiError body = mapper.readValue(response.body().string(), ApiError.class);
            return new ApiResponse<>(response.headers(), response.code(), null, body);
        } else {
            EditEmployeeResponse body = mapper.readValue(response.body().string(), EditEmployeeResponse.class);
            return new ApiResponse<>(response.headers(), response.code(), body, null);
        }
    }

    @Override
    public ApiResponse<CreateEmployeeResponse> create(String firstName, String lastName, String middleName, int companyId, String phone) throws IOException {
        HttpUrl url = getUrl().build();
        CreateEmployeeRequest bodyRequest = new CreateEmployeeRequest(firstName, lastName, middleName, companyId, phone);
        RequestBody jsonBody = RequestBody.create(mapper.writeValueAsString(bodyRequest), APPLICATION_JSON);
        Request.Builder request = new Request.Builder().post(jsonBody).url(url);
        if (token != null){
            request.addHeader("x-client-token", token);
        }

        Response response = this.client.newCall(request.build()).execute();

        if(response.code()>=400){
            ApiError body = mapper.readValue(response.body().string(), ApiError.class);
            return new ApiResponse<>(response.headers(), response.code(), null, body);
        } else {
            CreateEmployeeResponse body = mapper.readValue(response.body().string(), CreateEmployeeResponse.class);
            return new ApiResponse<>(response.headers(), response.code(), body, null);
        }
    }

    @NotNull
    private HttpUrl.Builder getUrl() {
        return HttpUrl.parse(BASE_PATH).newBuilder().addPathSegment(PATH);
    }

    public void setToken(String token) {
        this.token = token;
    }
}
