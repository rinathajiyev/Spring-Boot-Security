package com.company.securitydemo.auth;

import java.util.*;

public interface ApplicationUserDao {

    Optional<ApplicationUser> getApplicationUserByUsername(String username);
}
