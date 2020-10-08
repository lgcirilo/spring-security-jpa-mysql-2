package com.lgcirilo.springsecurityjpamysql2.security;

import com.lgcirilo.springsecurityjpamysql2.model.User;
import org.springframework.security.core.userdetails.UserDetails;
// TODO - continue from here
public class AppUserDetails implements UserDetails {
    public AppUserDetails(User user) {
    }
}
