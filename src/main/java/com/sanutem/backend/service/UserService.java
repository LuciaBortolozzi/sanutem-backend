package com.sanutem.backend.service;


import com.sanutem.backend.model.Role;
import com.sanutem.backend.model.Users;

import java.util.List;

public interface UserService {

    Users saveUser(Users user);
    Role saveRole(Role role);
    void addRoleToUser(String name, String roleName);
    Users getUser(String username);
    List<Users> getUsers();
}
