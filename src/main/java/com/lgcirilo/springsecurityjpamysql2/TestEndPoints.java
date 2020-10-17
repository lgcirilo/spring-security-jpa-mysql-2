package com.lgcirilo.springsecurityjpamysql2;

import com.lgcirilo.springsecurityjpamysql2.model.User;
import com.lgcirilo.springsecurityjpamysql2.repositories.UserRepository;
import com.lgcirilo.springsecurityjpamysql2.security.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class TestEndPoints {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    // TODO - remove after b64Decode method logic has been moved to AppUserDetailsService
    @Autowired
    private UserRepository userRepository;

    // TODO - remove after b64Decode method logic has been moved to AppUserDetailsService
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String root() {
        return "root";
    }

    @PostMapping(path = "/newUser", consumes = "application/json")
    public User saveNewUser(@RequestBody User user) {
        User newUser = user; // can this line be removed???
        return appUserDetailsService.save(newUser);
    }

    // TODO - move logic to AppUserDetailsService.
    // for testing purposes only
    @GetMapping(path = "/base64decode")
    public String b64Decode(@RequestParam String encryptedPassword) {
        byte[] decoded = Base64.getDecoder().decode(encryptedPassword);
        return new String(decoded);
    }

    // TODO - move logic to AppUserDetailsService
    // for testing purposes only
    @GetMapping(path = "/passwordMatches/{email}/{password}")
    public Boolean matches(@PathVariable String email, @PathVariable String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return passwordEncoder.matches(password, user.get().getPassword());
        }
        return null;
    }
}
