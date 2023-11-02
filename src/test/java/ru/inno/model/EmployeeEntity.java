package ru.inno.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class EmployeeEntity {
    private int id;
    @JsonProperty("is_active")
    private boolean isActive;
    @JsonProperty("create_timestamp")
    private Timestamp createDateTime;
    @JsonProperty("change_timestamp")
    private Timestamp lastChangedDateTime;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("middle_name")
    private String middleName;
    private String phone;
    private String email;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("company_id")
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
        EmployeeEntity that = (EmployeeEntity) o;
        return id == that.id && isActive == that.isActive && companyId == that.companyId && Objects.equals(createDateTime, that.createDateTime) && Objects.equals(lastChangedDateTime, that.lastChangedDateTime) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(middleName, that.middleName) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(avatarUrl, that.avatarUrl) && Objects.equals(birthdate, that.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, createDateTime, lastChangedDateTime, firstName, lastName, middleName, phone, email, avatarUrl, companyId, birthdate);
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
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
