package com.sanutem.backend.controller;

import com.sanutem.backend.model.Users;
import com.sanutem.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class UsersController {
    @Autowired
    private UsersRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Users n = new Users();
        n.setUserName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Users> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}