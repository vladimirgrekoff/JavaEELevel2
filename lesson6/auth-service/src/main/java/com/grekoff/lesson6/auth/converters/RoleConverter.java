package com.grekoff.lesson6.auth.converters;

import com.grekoff.lesson6.api.RoleDto;
import com.grekoff.lesson6.auth.entities.Role;
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
