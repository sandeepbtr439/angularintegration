package com.example.security.ApplicationUserRole;

import java.util.Set;

import com.example.security.ApplicationUserPermission.ApplicationUserPermission;
import com.google.common.collect.Sets;
public enum ApplicationUserRole {
STUDENT(Sets.new HashSet()),
ADMIN(Sets.new HashSet());
private final Set<ApplicationUserPermission> permissions;

private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
	this.permissions = permissions;
}
}
