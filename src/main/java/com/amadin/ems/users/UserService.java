package com.amadin.ems.users;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public UserDto getUserWithUsername(String username);

    public UserDto createGuestUser(String username, String password);

    public UserDto createUser(UserDto userDto);

    public Page<UserDto> getAllUsers(int size, int page, String sortField, String sortDirection);

     public UserDto getUserById(String id);

    public UserDto updateUser(String id, UserDto userDto);

    public UserDto getUserByIdAndStatus(String id, Boolean isActive);

    public UserDto deactivateUser(String id);

    public UserDto activateUser(String id);

    public void deleteUser(String id);



}
