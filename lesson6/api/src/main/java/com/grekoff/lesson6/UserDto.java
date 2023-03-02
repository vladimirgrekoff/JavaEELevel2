package com.grekoff.lesson6;

import java.util.Collection;


public class UserDto {
    public Long id;
    private String username;
    private String password;
    private String email;
    private Collection<RoleDto> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleDto> roles) {
        this.roles = roles;
    }

    public UserDto() {
    }

    public UserDto(Long id, String username, String password, String email, Collection<RoleDto> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
