package com.lgcirilo.springsecurityjpamysql2.security;

import com.lgcirilo.springsecurityjpamysql2.model.User;
import com.lgcirilo.springsecurityjpamysql2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        AppUserDetails appUserDetails = new AppUserDetails(user.get());
        return appUserDetails;
    }

    public User save(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
}
