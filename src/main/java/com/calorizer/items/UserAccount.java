package com.calorizer.items;

import java.util.Objects;

public class UserAccount extends Entity{

    private String username;
    private String password;
    private String personId;

    public UserAccount(int id, String username, String password, String personId) {
        super(id);
        this.username = username;
        this.password = password;
        this.personId = personId;
    }

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAccount(String username, String password, String personId) {
        this.username = username;
        this.password = password;
        this.personId = personId;
    }

    public UserAccount(int id, String username) {
        super(id);
        this.username = username;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, personId);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", personId='" + personId + '\'' +
                '}';
    }
}
