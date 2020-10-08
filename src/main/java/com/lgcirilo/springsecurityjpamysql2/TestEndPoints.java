package com.lgcirilo.springsecurityjpamysql2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class TestEndPoints {
    @GetMapping
    public String root() {
        return "root";
    }

}
