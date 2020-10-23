package com.lgcirilo.springsecurityjpamysql2.services;

import com.lgcirilo.springsecurityjpamysql2.model.Role;
import com.lgcirilo.springsecurityjpamysql2.model.User;
import com.lgcirilo.springsecurityjpamysql2.repositories.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    // IT'S RECOMMENDED TO USE CONSTRUCTOR BASED DEPENDENCY INJECTION. DO NOT USE @AUTOWIRED ANNOTATION
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    // TODO - make it return something if test for existing user fails. Maybe using ResponseEntity
    public User save(User user) {
        if (!userRepository.findByEmail(user.getEmail()).isPresent()) {
            List<Role> roles = new ArrayList<>();
            for (Role role : user.getRoles()) {
                roles.add(roleService.getRoleById(role.getId()));
            }
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setActive(true);
            newUser.setRoles(roles);
            return userRepository.save(newUser);
        }
        return null;
    }

    public void delete(long userId) {
        userRepository.deleteById(userId);
    }

    public User getUserById(long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
