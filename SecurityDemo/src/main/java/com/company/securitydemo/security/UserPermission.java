package com.company.securitydemo.security;

public enum UserPermission {
    STUDENT_WRITE("student:write"),
    STUDENT_READ("student:read"),
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read");

    private final String permission;

    UserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
