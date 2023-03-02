package com.grekoff.lesson7.auth.services;

import com.grekoff.lesson7.auth.entities.Role;
import com.grekoff.lesson7.auth.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}