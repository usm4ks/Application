package com.my.enums;

public enum UserRole {
    ADMIN("admin"),LIBRARIAN("librarian"),USER("user");

    private String role_name;

    UserRole(String role) {
        this.role_name = role;
    }

    public String getRoleName() {
        return role_name;
    }



}
