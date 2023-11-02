package ru.inno.model;

import java.util.Objects;

public class CreateCompanyRequest {
    private String name;
    private String description;

    public CreateCompanyRequest() {
    }

    public CreateCompanyRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateCompanyRequest that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription());
    }

    @Override
    public String toString() {
        return "CreateCompanyRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}