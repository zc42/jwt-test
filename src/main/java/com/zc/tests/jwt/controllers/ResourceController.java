package com.zc.tests.jwt.controllers;

import com.zc.tests.jwt.configurations.SecurityFilterChainConfig;
import com.zc.tests.jwt.utils.Greetings;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpSession;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class ResourceController {

    private final Log logger = LogFactory.getLog(ResourceController.class);

    @Value("${max.file.size}")
    private int maxMbSize;


    @PostMapping(value = "/resource", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("uploadFile");

        // Define the max size in bytes for this endpoint (e.g., 5MB)
//        int maxMbSize = 5;
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
}

