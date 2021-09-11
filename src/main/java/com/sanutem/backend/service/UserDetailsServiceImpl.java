package com.sanutem.backend.service;

import com.sanutem.backend.model.Role;
import com.sanutem.backend.model.Users;
import com.sanutem.backend.repository.RoleRepository;
import com.sanutem.backend.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserDetailsServiceImpl implements UserService,UserDetailsService {
    private final UsersRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Users userOptional = userRepository.findByUsername(username);
        /* ALTERNATIVA AL .orElseThrow
        if(userOptional==null){
        log.error("User not found in the database");
        throw new UsernameNotFoundException("User not found in the database");
        }else{
        log.error("User not found in the database: {}",username);
        }
        */
        Optional<Users> us = Optional.ofNullable(userOptional);
        Users user = us
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + username));
        //**************ROLES**************
        Collection<SimpleGrantedAuthority> authorities = new ArrayList();
        user.getRoles().forEach(role ->
        {authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        //**************ROLES**************
        return new org.springframework.security
                .core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true,
                true, authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }

    //**************ROLES**************
    @Override
    public Users saveUser(Users user) {
        log.info("Saving new user {} to the database", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        Users user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        Users us = user;
        user.getRoles().add(role);
    }

    @Override
    public Users getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Users> getUsers() {
        log.info("Fetching ALL user");
        return userRepository.findAll();
    }
    //**************ROLES**************
}
