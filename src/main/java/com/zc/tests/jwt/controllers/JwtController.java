package com.zc.tests.jwt.controllers;

import com.zc.tests.jwt.configurations.SecurityFilterChainConfig;
import com.zc.tests.jwt.utils.Greetings;
import jakarta.servlet.http.HttpSession;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Date;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class JwtController {

//    private final AuthenticationManager authenticationManager;
//
//    public JwtController(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    @Value("${jwt.secret_key}")
    private String key;

    private final Log logger = LogFactory.getLog(JwtController.class);


//    public JwtController(AuthenticationManager authenticationManager) {
//        logger.info("JwtController");
//        this.authenticationManager = authenticationManager;
//    }

//    @PostMapping("/jwt")
//    public String generateToken(@RequestBody AuthRequest authRequest) {
//        logger.info("generateToken");
//        try {
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
//            Authentication authentication = authenticationManager.authenticate(token);
//            boolean authenticated = authentication.isAuthenticated();
//
//        } catch (AuthenticationException e) {
//            throw new RuntimeException("Invalid credentials");
//        }
//        return getJWT(authRequest.getUsername());
//    }

    @GetMapping("/jwt")
    public String generateTokenV2() {
        return getJWT("some user");
    }

    @GetMapping("/requestMatchers")
    public String[] getRequestMatchers() {
        return SecurityFilterChainConfig.getRequestMatchers();
    }

    @PostMapping(value = "/file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("uploadFile");

        // Define the max size in bytes for this endpoint (e.g., 5MB)
        int maxMbSize = 5;
        long maxSize = maxMbSize * 1024 * 1024;
        if (file.getSize() > maxSize) {
            String message = MessageFormat.format("File size exceeds the limit of {0}MB.", maxMbSize);
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                    .body(message);
        }

        Path currentRelativePath = Paths.get("");
        String absolutePath = currentRelativePath.toAbsolutePath().toString();
        logger.info(MessageFormat.format("absolutePath: {0}", absolutePath));

        String originalFilename = file.getOriginalFilename();
        String date = LocalDateTime.now().toString();
        String filePath = MessageFormat.format("{0}/downloads/uploaded_file_{1}_{2}.txt", absolutePath, originalFilename, date);

        try {
            file.transferTo(new File(filePath));
            logger.info(MessageFormat.format("file uploaded to: {0}", filePath));
        } catch (IOException e) {
            String message = MessageFormat.format("Error occurred while uploading file: {0}.", filePath);
            logger.error(message, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @GetMapping
    public Greetings greetings(HttpSession session) {
        session.setAttribute("someName", "someValue");
        return Greetings.create(this.getClass(), session);
    }

    private String getJWT(String userName) {
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

//        Key key2 = Keys.hmacShaKeyFor(base64Key.getBytes());

//        String a = key.toString();
//        String result = new String(key.getEncoded(), StandardCharsets.UTF_8);
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
//                .signWith(key)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}

