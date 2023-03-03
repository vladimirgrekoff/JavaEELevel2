package com.grekoff.lesson7.auth.services;


import com.grekoff.lesson7.auth.entities.Role;
import com.grekoff.lesson7.auth.entities.User;
import com.grekoff.lesson7.api.UserDto;
import com.grekoff.lesson7.auth.converters.UserConverter;
import com.grekoff.lesson7.auth.exceptions.ResourceNotFoundException;
import com.grekoff.lesson7.auth.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final UserConverter userConverter;





    public List<UserDto> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> userList = usersRepository.findAll();
        for (User u: userList) {
            UserDto userDto = userConverter.entityToDto(u);
            userDtoList.add(userDto);
        }

        return userDtoList;
    }


    public Optional<User> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public Optional<User> findById(Long id) {
        return usersRepository.findById(id);
    }

    public User save(User user) {
        return usersRepository.save(user);
    }

    public User update(UserDto userDto) {
        User user = usersRepository.findById(userDto.getId()).orElseThrow(()-> new ResourceNotFoundException("Пользователь отсутствует в списке, id: " + userDto.getId()));
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }
}
