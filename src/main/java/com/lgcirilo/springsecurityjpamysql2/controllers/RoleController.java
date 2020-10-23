package com.lgcirilo.springsecurityjpamysql2.controllers;

import com.lgcirilo.springsecurityjpamysql2.model.Role;
import com.lgcirilo.springsecurityjpamysql2.repositories.RoleRepository;
import com.lgcirilo.springsecurityjpamysql2.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/role", produces = "application/json")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(path = "/{roleId}")
    public Role getRole(@PathVariable int roleId) {
        return roleService.getRoleById(roleId);
    }

}
