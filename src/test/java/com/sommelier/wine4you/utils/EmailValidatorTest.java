package com.sommelier.wine4you.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmailValidatorTest {
    private static EmailValidator emailValidator;
    private final String emailValid = "denys12@gmail.com";

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    @Test
    void isValid_Email_Ok() {
        assertTrue(emailValidator.isValid(emailValid, null));
    }

    @Test
    void isValid_notPresentEmailIsNull_notOk() {
        assertFalse(emailValidator.isValid(null, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "123",
            "asd.gmail.co",
            "asd@@gmail.co",
            "asd@*+gmail.co",
            "asda_gmail.co",
            "asdsad@",
            "asd.ds@asdsa@gmamam.com"})
    void isValid_notPresentEmail_notOk(String string) {
        assertFalse(emailValidator.isValid(string, null));
    }
}
