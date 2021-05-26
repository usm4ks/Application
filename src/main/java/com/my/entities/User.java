package com.my.entities;

import com.my.enums.UserRole;

public class User {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
    private boolean blocked;

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
