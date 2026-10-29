package com.amadin.ems.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.amadin.ems.exception.BadRequestException;
import com.amadin.ems.security.JwtService;
import com.amadin.ems.users.User;
import com.amadin.ems.users.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse autheticateLogin(AuthRequest request) {

        User user = null;
        String token = "";

        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (!authenticate.isAuthenticated()) {
                throw new BadRequestException("Invalid Credentials");
            }

            user = (User) authenticate.getPrincipal();
            user.setPassword("");
            token = jwtService.generateToken(request.getUsername());

        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new BadRequestException(e.getMessage());
        }

        return new AuthResponse(token, UserMapper.mapToUserDTO(user));
    }

}
