package ru.inno.model;

import java.util.Objects;

public class CreateEmployeeResponse {

    private int id;

    public CreateEmployeeResponse() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateEmployeeResponse that = (CreateEmployeeResponse) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CreateEmployeeResponse{" +
                "id=" + id +
                '}';
    }
}
