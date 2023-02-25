package com.company.securitydemo.security;

import com.google.common.collect.*;
import org.springframework.security.core.authority.*;

import java.util.*;
import java.util.stream.*;

import static com.company.securitydemo.security.UserPermission.*;

public enum UserRole {
    ADMIN(Sets.newHashSet(COURSE_WRITE, COURSE_READ, STUDENT_READ, STUDENT_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ)),
    STUDENT(Sets.newHashSet());

    private final Set<UserPermission> userPermission;

    UserRole(Set<UserPermission> userPermission){
        this.userPermission=userPermission;
    }

    public Set<UserPermission> getUserPermission(){
        return userPermission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getUserPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
