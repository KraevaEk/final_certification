package ru.inno.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;


public class Employee {
    private int id;
    @JsonProperty("isActive")
    private boolean isActive;
    private Timestamp createDateTime;
    private Timestamp lastChangedDateTime;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    private int companyId;
    private Date birthdate;

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

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Timestamp getLastChangedDateTime() {
        return lastChangedDateTime;
    }

    public void setLastChangedDateTime(Timestamp lastChangedDateTime) {
        this.lastChangedDateTime = lastChangedDateTime;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && isActive == employee.isActive && companyId == employee.companyId && Objects.equals(createDateTime, employee.createDateTime) && Objects.equals(lastChangedDateTime, employee.lastChangedDateTime) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(middleName, employee.middleName) && Objects.equals(phone, employee.phone) && Objects.equals(email, employee.email) && Objects.equals(avatarUrl, employee.avatarUrl) && Objects.equals(birthdate, employee.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, createDateTime, lastChangedDateTime, firstName, lastName, middleName, phone, email, avatarUrl, companyId, birthdate);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", createDateTime=" + createDateTime +
                ", lastChangedDateTime=" + lastChangedDateTime +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", companyId=" + companyId +
                ", birthdate=" + birthdate +
                '}';
    }
}
