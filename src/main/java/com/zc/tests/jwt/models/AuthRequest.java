package com.zc.tests.jwt.models;

import com.zc.tests.jwt.controllers.JwtController;
import lombok.Getter;
import lombok.Setter;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.text.MessageFormat;


@Setter
@Getter
public class AuthRequest {

    // Getters and Setters
    private String username;
    private String password;

    private final Log logger = LogFactory.getLog(AuthRequest.class);


    // Default constructor
    public AuthRequest() {
        logger.info("ai");
    }

    // Parameterized constructor
    public AuthRequest(String username, String password) {
        logger.info(MessageFormat.format("{0} {1}", username, password));
        this.username = username;
        this.password = password;
    }

    // Optionally, override toString() for debugging
    @Override
    public String toString() {
        return "AuthRequest{" +
                "username='" + username + '\'' +
                ", password='[PROTECTED]'}";
    }
}
