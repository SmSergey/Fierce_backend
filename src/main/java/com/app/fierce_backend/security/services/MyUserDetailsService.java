package com.app.fierce_backend.security.services;

import com.app.fierce_backend.domain.user.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService  {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        val user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        if(!user.getIsActivated()) {
            throw new UsernameNotFoundException(username);
        }

        return new User(String.valueOf(user.getEmail()), user.getPassword(),  new ArrayList<>());
    }
}
