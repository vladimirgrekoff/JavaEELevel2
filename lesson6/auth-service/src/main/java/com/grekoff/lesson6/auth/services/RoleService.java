package com.grekoff.lesson6.auth.services;

import com.grekoff.lesson6.auth.repositories.RoleRepository;
import com.grekoff.lesson6.auth.entities.Role;
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