package com.sommelier.wine4you.security.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.util.ReflectionTestUtils;

class JwtTokenProviderTest {
    private static Authentication authentication;
    private static JwtTokenProvider jwtTokenProvider;
    private static HttpServletRequest request;
    private String login;
    private String password;
    private String token;

    @BeforeAll
    static void beforeAll() {
        authentication = Mockito.mock(Authentication.class);
        request = Mockito.mock(HttpServletRequest.class);
        jwtTokenProvider = new JwtTokenProvider();
        ReflectionTestUtils.setField(jwtTokenProvider, "jwtSecret", "den.shl");
        ReflectionTestUtils.setField(jwtTokenProvider, "validityJwtInMilliseconds", 3600000);
//        jwtTokenProvider.g;
    }

    @BeforeEach
    void setUp() {
        login = "denys@gmail.com";
        password = "qaz!23";
        Mockito.when(authentication.getName()).thenReturn(login);
        token = jwtTokenProvider.generateToken(authentication);
    }

    @Test
    void createToken_valid_Ok() {
        Mockito.when(authentication.getName()).thenReturn(login);
        String actualToken
                = jwtTokenProvider.generateToken(authentication);
        assertNotNull(actualToken);
        String[] arr = actualToken.split("\\.");
        assertEquals(3, arr.length);
    }

//    @Test
//    void getAuthentication_valid_Ok() {
//        UserDetails userDetails = User.withUsername(login)
//                .password(password)
//                .roles(Role.RoleName.USER.name())
//                .build();
//        Mockito.when(userDetailsService.loadUserByUsername(login)).thenReturn(userDetails);
//        Authentication authenticationActual = jwtTokenProvider.getAuthentication(token);
//        assertNotNull(authenticationActual);
//        assertEquals(login, authenticationActual.getName());
//        User actualPrincipal = (User) authenticationActual.getPrincipal();
//        assertEquals(login, actualPrincipal.getUsername());
//        assertEquals(password, actualPrincipal.getPassword());
//        assertEquals("ROLE_USER", actualPrincipal.getAuthorities()
//                .stream()
//                .parallel()
//                .findFirst()
//                .get()
//                .getAuthority());
//    }

    @Test
    void getUsername_validUsername_Ok() {
        String actual = jwtTokenProvider.getUsernameFromJwT(token);
        assertEquals(login, actual);
    }

//    @Test
//    void resolveToken_valid_OK() {
//        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
//        String bearerToken = request.getHeader("Authorization");
//        assertTrue(bearerToken.startsWith("Bearer "));
//        String resolveToken = jwtTokenProvider.validateToken(request);
//        assertEquals(token, resolveToken);
//    }
//
//    @Test
//    void resolveToken_notPresent_notOk() {
//        Mockito.when(request.getHeader("Authorization")).thenReturn("Basic");
//        String resolveTokenFalse = jwtTokenProvider.resolveToken(request);
//        assertNull(resolveTokenFalse);
//    }

    @Test
    void validateToken_Ok() {
        boolean validateToken = jwtTokenProvider.validateToken(token);
        assertTrue(validateToken);
    }

    @Test
    void validateToken_expected_Exception_notOk() {
        assertThrows(RuntimeException.class, () -> {
            jwtTokenProvider.validateToken("sdD$gse^rg.r34252525wt4fr+g.df");
        });
    }

    @Test
    void validateToken_expected_InvalidJwtAuthenticationException_notOk() {
        try {
            jwtTokenProvider.validateToken("");
        } catch (RuntimeException e) {
            assertEquals("Expired or invalid JWT token", e.getMessage());
            return;
        }
        fail("Expected to receive InvalidJwtAuthenticationException for incorrect token");
    }
}
