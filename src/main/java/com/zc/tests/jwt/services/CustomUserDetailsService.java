package com.zc.tests.jwt.services;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Log logger = LogFactory.getLog(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Replace with your user-fetching logic (e.g., from database)
        logger.info("loadUserByUsername");
        if ("u".equals(username)) {
            String password = new BCryptPasswordEncoder().encode("p");
            return new User("u", password, Collections.emptyList());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
