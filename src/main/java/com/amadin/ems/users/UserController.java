package com.amadin.ems.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // @GetMapping("/newuser")
    // public ResponseEntity<User> createGuestUser(
    // @RequestParam String username,
    // @RequestParam String password) {
    // User createdUser = userService.createNewUser(username, password);

    // return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    // }

    // CREATE USER REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
    }

    // GET ALL USERS REST API
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUser(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "DESC") String sortDirection,
            @RequestParam(defaultValue = "createdAt") String sortField) {

        Page<UserDto> users = userService.getAllUsers(size, page, sortField, sortDirection);

        return ResponseEntity.ok(users);

    }

      // GET USER BY ID REST API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") String userId) {
        UserDto user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // UPDATE USER REST API
    @PatchMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") String userId, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userId, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    // DEACTIVATE USER REST API
    @PatchMapping("deactivate/{id}")
    public ResponseEntity<UserDto> deactivateUser(@PathVariable("id") String userId) {
        UserDto updatedUser = userService.deactivateUser(userId);
        return ResponseEntity.ok(updatedUser);
    }

    // ACTIVATE USER REST API
    @PatchMapping("activate/{id}")
    public ResponseEntity<UserDto> activateUser(@PathVariable("id") String userId) {
        UserDto updatedUser = userService.activateUser(userId);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE USER REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User was deleted successfully");
    }
}
