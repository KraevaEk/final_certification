package ru.inno.model;

import java.util.Objects;

public record ApiError(int statusCode, String message) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiError apiError)) return false;
        return statusCode() == apiError.statusCode() && Objects.equals(message(), apiError.message());
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode(), message());
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}