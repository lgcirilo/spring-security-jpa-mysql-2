package com.lgcirilo.springsecurityjpamysql2.security;

import com.lgcirilo.springsecurityjpamysql2.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// TODO - continue from here
public class AppUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private Set<GrantedAuthority> authorities;

    Logger logger = LoggerFactory.getLogger(AppUserDetails.class);

    public AppUserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        // TODO - The code below grants a fixed authority. Change it to grant appropriate authorities.
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_USER");
        this.authorities = new HashSet<>();
        authorities.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // TODO - change methods below to return actual values taken from each user. Changes need to be made
    // TODO - in the database to add related fields to user table.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
