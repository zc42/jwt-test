package com.zc.tests.jwt.utils;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(staticName = "from")
public class Greetings  {

    private final String[] greetings;
    private final List<String> availableEndpoints;

    public static Greetings create(Class<?> clazz, HttpSession httpSession) {

        List<String> sessionAttributeNames=new ArrayList<>();
        httpSession.getAttributeNames().asIterator().forEachRemaining(sessionAttributeNames::add);
        String attributeNames = sessionAttributeNames.stream().map(Object::toString).collect(Collectors.joining("/n"));

        String msg = MessageFormat.format("Hello from {0}\ngoto to /swagger-ui/index.html for some ui\n" +
                        "Session attributes:\n{1}\n" +
                        "Session id: {2}\n",
                clazz.getSimpleName(),
                attributeNames,
                httpSession.getId());
        String[] greetings = msg.split("\n");

        Method[] methods = clazz.getDeclaredMethods();
        List<String> availableEndpoints = Arrays.stream(methods)
                .map(Greetings::getMappingValue)
                .filter(Objects::nonNull)
                .toList();

        return Greetings.from(greetings, availableEndpoints);
    }

    private static final List<Class<? extends Annotation>> mappingClasses = List.of(
            GetMapping.class, PostMapping.class,
            PutMapping.class, DeleteMapping.class,
            PatchMapping.class);

    private static String getMappingValue(Method method) {
        return mappingClasses.stream()
                .filter(method::isAnnotationPresent)
                .findFirst()
                .map(method::getAnnotation)
                .map(Greetings::getMappingValue)
                .orElse(null);
    }

    private static String getMappingValue(Annotation annotation) {
        if (annotation instanceof GetMapping mapping) return "GET " + toString(mapping.value());
        if (annotation instanceof PostMapping mapping) return "POST " + toString(mapping.value());
        if (annotation instanceof PutMapping mapping) return "PUT " + toString(mapping.value());
        if (annotation instanceof DeleteMapping mapping) return "DELETE " + toString(mapping.value());
        if (annotation instanceof PatchMapping mapping) return "PATCH " + toString(mapping.value());
        return null;
    }

    private static String toString(String[] mapping) {
        if (mapping.length == 0) return "";
        return String.join(", ", mapping);
    }
}
