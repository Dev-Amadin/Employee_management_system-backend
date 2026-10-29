package com.amadin.ems.security.auth;

import com.amadin.ems.users.UserDto;

public record AuthResponse(String token, UserDto user) {

}
