package com.amadin.ems.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.amadin.ems.exception.BadRequestException;
import com.amadin.ems.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String autheticateLogin(AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (!authenticate.isAuthenticated()) {
                throw new BadRequestException("Invalid Credentials");
            }
        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new BadRequestException(e.getMessage());
        }

        return jwtService.generateToken(request.getUsername());
    }

}
