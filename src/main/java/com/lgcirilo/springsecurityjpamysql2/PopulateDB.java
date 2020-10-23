//package com.lgcirilo.springsecurityjpamysql2;
//
//import com.lgcirilo.springsecurityjpamysql2.model.Role;
//import com.lgcirilo.springsecurityjpamysql2.model.User;
//import com.lgcirilo.springsecurityjpamysql2.services.RoleService;
//import com.lgcirilo.springsecurityjpamysql2.services.UserService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Date;
//
//@Configuration
//public class PopulateDB {
//    @Bean
//    CommandLineRunner dataLoader(UserService userService, RoleService roleService) {
//        return args -> {
//            User user1 = new User();
//            User user2 = new User();
//            Role roleUser = new Role("ROLE_USER");
//            Role roleAdmin = new Role("ROLE_ADMIN");
//            user1.setEmail("lgcirilo@gmail.com");
//            user1.setPassword("admin");
//            user1.setActive(true);
//            user2.setEmail("guiguid.cirilo@gmail.com");
//            user2.setPassword("user");
//            user2.setActive(true);
//            userService.save(user1);
//            userService.save(user2);
//            roleService.save(roleUser);
//            roleService.save(roleAdmin);
//
//        };
//    }
//}
