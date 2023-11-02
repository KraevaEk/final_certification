package ru.inno.model;

import java.util.Objects;

public class UserInfo {
    private String userToken;
    private String role;
    private String displayName;
    private String login;

    public UserInfo() {
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo userInfo)) return false;
        return Objects.equals(getUserToken(), userInfo.getUserToken()) && Objects.equals(getRole(), userInfo.getRole()) && Objects.equals(getDisplayName(), userInfo.getDisplayName()) && Objects.equals(getLogin(), userInfo.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserToken(), getRole(), getDisplayName(), getLogin());
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userToken='" + userToken + '\'' +
                ", role='" + role + '\'' +
                ", displayName='" + displayName + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}