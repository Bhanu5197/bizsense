package org.example.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@EnableWebSecurity
public class SecurityConfigTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testUserDetailsService() {
        assertNotNull(userDetailsService);
        UserDetails user = userDetailsService.loadUserByUsername("user");
        assertNotNull(user);
        assertEquals("user", user.getUsername());
        // Add more assertions as needed for user details

        UserDetails admin = userDetailsService.loadUserByUsername("admin");
        assertNotNull(admin);
        assertEquals("admin", admin.getUsername());
        // Add more assertions as needed for admin details
    }

    @Test
    public void testPasswordEncoder() {
        assertNotNull(passwordEncoder);
        String encodedPassword = passwordEncoder.encode("password");
        assertNotNull(encodedPassword);
        // Add assertions for encoded passwords
    }
}
