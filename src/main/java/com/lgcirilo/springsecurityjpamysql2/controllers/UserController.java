package com.lgcirilo.springsecurityjpamysql2.controllers;

import com.lgcirilo.springsecurityjpamysql2.model.User;
import com.lgcirilo.springsecurityjpamysql2.services.RoleService;
import com.lgcirilo.springsecurityjpamysql2.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user", produces = "application/json")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
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
//
//     @GetMapping(path = "/{userId}")
//    public User getUserById(@PathVariable long userId) {
//        Optional<User> fetchedUser = userService.getUserById(userId);
//        if (fetchedUser.isPresent()) {
//            return fetchedUser.get();
//        }
//        return null;
//    }

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
            User patchedUser = newUser;
            patchedUser.setActive(user.isActive());
            if (user.getEmail() != null) {
                patchedUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                patchedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return userService.save(patchedUser); // TODO - use ResponseEntity
        }
        return null; // TODO - use ResponseEntity
    }
}
