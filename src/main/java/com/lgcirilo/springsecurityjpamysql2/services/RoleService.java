package com.lgcirilo.springsecurityjpamysql2.services;

import com.lgcirilo.springsecurityjpamysql2.model.Role;
import com.lgcirilo.springsecurityjpamysql2.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(int roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent()) {
            return role.get();
        }
        return null;
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public void delete(int roleId) {
        roleRepository.deleteById(roleId);
    }
}
