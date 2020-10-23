package com.lgcirilo.springsecurityjpamysql2.controllers;

import com.lgcirilo.springsecurityjpamysql2.model.Role;
import com.lgcirilo.springsecurityjpamysql2.model.User;
import com.lgcirilo.springsecurityjpamysql2.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user", produces = "application/json")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

    @GetMapping
    public User getUserById(@RequestParam long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping(path = "/new", consumes = "application/json")
    public User saveNewUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping(path = "/delete/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }

    @PatchMapping
    public User patchUser(@RequestBody User user) {
        User newUser = userService.getUserById(user.getId());
        if (newUser != null) {
            // TODO - move if statement body to a method in UserService
            newUser.setActive(user.isActive());
            if (user.getEmail() != null) {
                newUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (user.getRoles() != null) {
                for (Role role : user.getRoles()) {
                    if (!newUser.getRoles().contains(role)) {
                        newUser.getRoles().add(role);
                    }
                }
            }
            return userService.save(newUser); // TODO - use ResponseEntity
        }
        return null; // TODO - use ResponseEntity
    }
}
