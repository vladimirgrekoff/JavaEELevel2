package com.grekoff.lesson5.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.grekoff.lesson5.core.entities.Role;
import com.grekoff.lesson5.core.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}