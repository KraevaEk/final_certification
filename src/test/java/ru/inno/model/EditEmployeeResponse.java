package ru.inno.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class EditEmployeeResponse {

    private int id;
    @JsonProperty("isActive")
    private boolean isActive;
    private String email;
    @JsonProperty("url")
    private String urlEmployee;

    public EditEmployeeResponse(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlEmployee() {
        return urlEmployee;
    }

    public void setUrlEmployee(String urlEmployee) {
        this.urlEmployee = urlEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditEmployeeResponse that = (EditEmployeeResponse) o;
        return id == that.id && isActive == that.isActive && Objects.equals(email, that.email) && Objects.equals(urlEmployee, that.urlEmployee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, email, urlEmployee);
    }

    @Override
    public String toString() {
        return "EditEmployeeResponse{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", email='" + email + '\'' +
                ", urlEmployee='" + urlEmployee + '\'' +
                '}';
    }
}
