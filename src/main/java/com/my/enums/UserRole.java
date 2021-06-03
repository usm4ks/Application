package com.my.enums;

public enum UserRole {
    ADMIN("admin"),LIBRARIAN("librarian"),USER("user");

    private final String roleName;

    UserRole(String role) {
        this.roleName = role;
    }

    public String getRoleName() {
        return roleName;
    }



}
