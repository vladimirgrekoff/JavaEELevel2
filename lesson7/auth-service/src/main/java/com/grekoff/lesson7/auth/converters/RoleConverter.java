package com.grekoff.lesson7.auth.converters;

import com.grekoff.lesson7.auth.entities.Role;
import com.grekoff.lesson7.api.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public Role dtoToEntity(RoleDto roleDto) {
        return new Role(roleDto.getId(), roleDto.getName());
    }

    public RoleDto entityToDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }
}
