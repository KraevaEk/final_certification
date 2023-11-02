package ru.inno.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import ru.inno.model.UserInfo;

import java.io.IOException;

public class AuthorizeServiceImpl implements AuthorizeService {
    public static final MediaType APPLICATION_JSON = MediaType.parse("application/json; charset=UTF-8");
    private static final String PATH = "/auth/login";
    private final String BASE_PATH;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public AuthorizeServiceImpl(OkHttpClient client, String url) {
        this.client = client;
        this.BASE_PATH = url;
        this.mapper = new ObjectMapper();
    }

    @Override
    public UserInfo auth(String username, String password) throws IOException {
        String body = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
        RequestBody jsonBody = RequestBody.create(body, APPLICATION_JSON);
        Request request = new Request.Builder().post(jsonBody).url(BASE_PATH + PATH).build();
        Response response = this.client.newCall(request).execute();
        return mapper.readValue(response.body().string(), UserInfo.class);
    }
}