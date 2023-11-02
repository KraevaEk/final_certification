package ru.inno.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class EditEmployeeRequest {

    private String lastName;
    private String email;
    @JsonProperty("url")
    private String urlEmployee;
    private String phone;
    @JsonProperty("isActive")
    private boolean isActive;

    public EditEmployeeRequest(String lastName, String email, String urlEmployee, String phone, boolean isActive) {
        this.lastName = lastName;
        this.email = email;
        this.urlEmployee = urlEmployee;
        this.phone = phone;
        this.isActive = isActive;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditEmployeeRequest that = (EditEmployeeRequest) o;
        return isActive == that.isActive && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(urlEmployee, that.urlEmployee) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, email, urlEmployee, phone, isActive);
    }

    @Override
    public String toString() {
        return "EditEmployeeRequest{" +
                "lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", urlEmployee='" + urlEmployee + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
