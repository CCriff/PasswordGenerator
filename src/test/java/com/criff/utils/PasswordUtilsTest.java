package com.criff.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilsTest {

    private PasswordUtils passwordUtils;
    private final String plainTextPassword = "testpassword";
    private String encryptedPassword;

    @BeforeEach
    void setUp() {
        passwordUtils = new PasswordUtils();
    }

    @AfterEach
    void tearDown() {
        passwordUtils = null;
    }

    @Test
    void encrypt_givenPlainTextPassword_returnsEncryptedPassword() {
        encryptedPassword = passwordUtils.encrypt(plainTextPassword);
        assertNotEquals(plainTextPassword, encryptedPassword);
    }

    @Test
    void decrypt_givenEncryptedPassword_returnsPlainTextPassword() {
        encryptedPassword = passwordUtils.encrypt(plainTextPassword);
        String decryptedPassword = passwordUtils.decrypt(encryptedPassword);
        assertEquals(plainTextPassword, decryptedPassword);
    }

    @Test
    void generateSecurePassword_givenPlainTextPassword_returnsEncryptedPassword() {
        encryptedPassword = passwordUtils.generateSecurePassword(plainTextPassword);
        assertNotEquals(plainTextPassword, encryptedPassword);
    }

    @Test
    void verifyPassword_givenPlainTextPasswordAndEncryptedPassword_returnsTrue() {
        encryptedPassword = passwordUtils.generateSecurePassword(plainTextPassword);
        boolean isMatch = passwordUtils.verifyPassword(plainTextPassword, encryptedPassword);
        assertTrue(isMatch);
    }

    @Test
    void generateRandomPassword_returnsRandomPassword() {
        String randomPassword = passwordUtils.generateRandomPassword();
        assertNotNull(randomPassword);
    }
}