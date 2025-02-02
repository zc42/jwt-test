package com.zc.tests.jwt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class JwtApplication {

    private final static Log logger = LogFactory.getLog(JwtApplication.class);
    public static void main(String[] args) {

        ConfigurableApplicationContext apc = SpringApplication.run(JwtApplication.class, args);
        logger.info("******************************   BeanDefinitionNames -- start");
        Arrays.stream(apc.getBeanDefinitionNames()).sorted().forEach(logger::info);
        logger.info("******************************   BeanDefinitionNames -- end");
    }

}
