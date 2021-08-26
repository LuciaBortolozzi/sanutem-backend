package com.sanutem.backend.controller;

import com.sanutem.backend.model.Role;
import com.sanutem.backend.model.Users;
import com.sanutem.backend.repository.UsersRepository;
import com.sanutem.backend.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
@RequiredArgsConstructor
public class UsersController {
    @Autowired
    private UsersRepository userRepository;

    private final UserService userService;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Users n = new Users();
        n.setUsername(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Users> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    //ROLES
    @GetMapping(path="/users_with_roles")
    public ResponseEntity<List<Users>> getUsers() {
        // This returns a JSON or XML with the users
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping(path="/user_with_roles/save")
    public ResponseEntity<Users> saveUsers(@RequestBody Users user) {
        // This returns a JSON or XML with the users
        URI uri =   URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users_with_roles/user_with_roles/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    //este no lo vamos a necesitar
    @PostMapping(path="/role/save")
    public ResponseEntity<Role> saveRoles(@RequestBody Role role) {
        URI uri =   URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users_with_roles/role/save").toUriString());
        // This returns a JSON or XML with the users
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    //------------------------------------

    @PostMapping(path="/role/addToUser")
    public ResponseEntity<?> addRoleToUsers(@RequestBody RoleToUserForm form) {
        // This returns a JSON or XML with the
        userService.addRoleToUser(form.getUserName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
    //ROLES
}
//ROLES
    @Data
    class RoleToUserForm{

        private String userName;
        private String roleName;
    }
//ROLES