package com.sommelier.wine4you.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.sommelier.wine4you.model.Role;
import com.sommelier.wine4you.model.User;
import com.sommelier.wine4you.service.UserService;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

class CustomUserDetailsServiceTest {
    private static CustomUserDetailsService userDetailsService;
    private User bob;

    @BeforeAll
    static void beforeAll() {
        userDetailsService = Mockito.mock(CustomUserDetailsService.class);
    }

    @BeforeEach
    void setUp() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        bob = getTestUser(
                "Den",
                "Sh",
                "7860@gmail.com",
                "+380501321232",
                "147147147",
                Set.of(role));
    }

    @Test
    void loadUserByUsername_Ok() {
        org.springframework.security.core.userdetails.User userByDetailsService
                = new org.springframework.security.core.userdetails.User(
                bob.getEmail(), bob.getPassword(), bob.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList()));
        Mockito.when(userDetailsService.loadUserByUsername(bob.getEmail()))
                .thenReturn(userByDetailsService);
        UserDetails actual = userDetailsService.loadUserByUsername(bob.getEmail());
        assertNotNull(actual);
        assertEquals(bob.getEmail(), actual.getUsername());
        assertEquals(bob.getPassword(), actual.getPassword());
    }

    @Test
    void loadUserByUsername_nonExistentEmail_notOk() {
        assertThrows(Exception.class, () -> userDetailsService.loadUserByUsername(any()));
    }

    private User getTestUser(String firstName,
                             String lastName,
                             String email,
                             String phone,
                             String password,
                             Set<Role> roles) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setRoles(roles);
        return user;
    }
}
