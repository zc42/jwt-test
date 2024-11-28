//package com.zc.tests.jwt.configurations;
//
//import com.zc.tests.jwt.services.CustomBCryptPasswordEncoder;
//import com.zc.tests.jwt.services.CustomUserDetailsService;
//import org.apache.juli.logging.Log;
//import org.apache.juli.logging.LogFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
//
//@Configuration
//public class AuthenticationConfig {
//
//    //    private final CustomBCryptPasswordEncoder passwordEncoder;
////    private final CustomUserDetailsService userDetailsService;
////
//    private final Log logger = LogFactory.getLog(AuthenticationConfig.class);
////
////    public AuthenticationConfig(CustomBCryptPasswordEncoder passwordEncoder,
////                                CustomUserDetailsService userDetailsService
////    ) {
////        this.passwordEncoder = passwordEncoder;
////        this.userDetailsService = userDetailsService;
////    }
////
////    @Bean
////    public AuthenticationProvider authenticationProvider() {
////        logger.info("authenticationProvider");
////        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////        provider.setUserDetailsService(userDetailsService);
////        provider.setPasswordEncoder(passwordEncoder);
////        return provider;
////    }
//
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
////        // Use AuthenticationConfiguration to get the AuthenticationManager
////        logger.info("authenticationManager");
////        return authenticationProvider::authenticate;
////    }
//
//
//}
