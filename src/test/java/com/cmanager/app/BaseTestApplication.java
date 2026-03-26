package com.cmanager.app;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BaseTestApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String PASSWORD_INVALID = "$2a$10$JXZFFAfBRw38kKLQ13Hm0eSn1MzYSpixE8Ble9paV4tHOFgbPQKhW";

    private static final String PASSWORD_VALID = "$2a$10$kjpgD7lsBXHzEYb0c9u.FOfAmTkniKZspJuHyI4/9LYfqvh4Q75Hq";

    private static final String password ="admin";

    @Test
    public void validPasswordEncoder() {

        final var encoded = passwordEncoder.encode(password);
        assertTrue(passwordEncoder.matches(password, encoded));

    }
}
