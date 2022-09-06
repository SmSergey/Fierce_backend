package com.app.fierce_backend.security.filters;

import com.app.fierce_backend.domain.user.interfaces.UserRepository;
import com.app.fierce_backend.security.filters.jwt.JwtFilter;
import com.app.fierce_backend.security.jwt.JwtTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FiltersFactory {

    private final JwtTokenRepository jwtTokenRepository;
    private final UserRepository userRepository;

    public JwtFilter getJwtFilter() {
        return new JwtFilter(jwtTokenRepository, userRepository);
    }
}
