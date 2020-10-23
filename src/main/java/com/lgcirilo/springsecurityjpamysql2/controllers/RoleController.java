package com.lgcirilo.springsecurityjpamysql2.controllers;

import com.lgcirilo.springsecurityjpamysql2.model.Role;
import com.lgcirilo.springsecurityjpamysql2.services.RoleService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/new")
    public Role addNewRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    @DeleteMapping(path = "/delete/{roleId}")
    public void deleteRole(@PathVariable int roleId) {
        roleService.delete(roleId);
    }
}
