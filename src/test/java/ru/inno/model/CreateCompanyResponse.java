package ru.inno.model;

import java.util.Objects;

public class CreateCompanyResponse {
    private int id;

    public CreateCompanyResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreateCompanyResponse{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateCompanyResponse that)) return false;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}