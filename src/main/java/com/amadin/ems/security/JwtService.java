package com.amadin.ems.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    public static final String SECRET = "MyDamnNearSecret24244545KeyUnTRACKEDandUNtraced";

    private SecretKey getSignedKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .claims(new HashMap<>())
                .signWith(getSignedKey())
                .compact();

    }

    public Claims verifySignatureAndExtractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignedKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public String extractUsername(String token) {
        return verifySignatureAndExtractAllClaims(token).getSubject();
    }

    public Date getExpiration(String token) {
        return verifySignatureAndExtractAllClaims(token).getExpiration();
    }

    public Boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

}
