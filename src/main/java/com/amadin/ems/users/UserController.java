package com.amadin.ems.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ems/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/newuser")
    public ResponseEntity<User> createNewUser(
            @RequestParam String username,
            @RequestParam String password) {
        User createdUser = userService.createNewUser(username, password);

        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

}
