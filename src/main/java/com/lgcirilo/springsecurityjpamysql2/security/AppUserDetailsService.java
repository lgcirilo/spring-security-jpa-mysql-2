package com.lgcirilo.springsecurityjpamysql2.security;

import com.lgcirilo.springsecurityjpamysql2.model.User;
import com.lgcirilo.springsecurityjpamysql2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return new AppUserDetails(user.get());
    }
}
