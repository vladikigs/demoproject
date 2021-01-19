package com.calculatorserver.demoproject.config;

import java.util.Set;
import com.google.common.collect.Sets;

import static com.calculatorserver.demoproject.config.ApplicationPermission.*;

public enum ApplicationRole {
    USER(Sets.newHashSet(USER_READ, USER_WRITE)), ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE));

    private final Set<ApplicationPermission> applicationPermissions;

    public Set<ApplicationPermission> getApplicationPermissions() {
        return applicationPermissions;
    }

    ApplicationRole(Set<ApplicationPermission> permissions) {
        this.applicationPermissions = permissions;
    }
}
