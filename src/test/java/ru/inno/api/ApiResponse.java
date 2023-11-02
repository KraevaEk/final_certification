package ru.inno.api;

import okhttp3.Headers;
import ru.inno.model.ApiError;

import java.util.Objects;

public class ApiResponse<T> {
    private final Headers headers;
    private final int statusCode;
    private final T body;

    private final ApiError apiError;
    public ApiResponse(Headers headers, int statusCode, T body, ApiError apiError) {
        this.headers = headers;
        this.statusCode = statusCode;
        this.body = body;
        this.apiError = apiError;
    }

    public Headers getHeaders() {
        return headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getBody() {
        return body;
    }

    public ApiError getApiError() {
        return apiError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiResponse<?> that)) return false;
        return getStatusCode() == that.getStatusCode() && Objects.equals(getHeaders(), that.getHeaders()) && Objects.equals(getBody(), that.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeaders(), getStatusCode(), getBody());
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "headers=" + headers +
                ", statusCode=" + statusCode +
                ", body=" + body +
                '}';
    }
}