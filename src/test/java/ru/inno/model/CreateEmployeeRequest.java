package ru.inno.model;

import java.util.Objects;

public class CreateEmployeeRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private int companyId;
    private String phone;

    public CreateEmployeeRequest(String firstName, String lastName, String middleName, int companyId, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.companyId = companyId;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateEmployeeRequest that = (CreateEmployeeRequest) o;
        return companyId == that.companyId && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(middleName, that.middleName) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName, companyId, phone);
    }

    @Override
    public String toString() {
        return "CreateEmployeeRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", companyId=" + companyId +
                ", phone='" + phone + '\'' +
                '}';
    }
}
