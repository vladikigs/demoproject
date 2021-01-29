package com.calculatorserver.demoproject.config;

public enum ApplicationPermission {

    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    public String getPermission() {
        return permission;
    }

    private final String permission;

    ApplicationPermission(String permission) {
        this.permission = permission;
    }
}
