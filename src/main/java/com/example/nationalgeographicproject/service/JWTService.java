package com.example.nationalgeographicproject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JWTService {
    private final JwtEncoder jwtEncoder;

    public String jwtToken(Authentication authentication) {
        var now = Instant.now();

        //[{authority: "ROLE_ADMIN"}, {authority: "ROLE_USER"}]
        String scope = authentication
                .getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(" ")); //"ROLE_ADMIN ROLE_USER"


        var claims = JwtClaimsSet
                .builder()
                .issuer("self")
                .issuedAt(now)
                .subject(authentication.getName())
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .claim("scope", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
//React Register email
//React Login username, password