package com.criff.passwordgenerator.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.GeneralSecurityException;

import com.criff.passwordgenerator.utils.PasswordUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordUtilsTest {

    // Test string constant
    private static final String TEST_STRING = "test string";
    // Test password constant
    private static final String TEST_PASSWORD = "test password";
    // Encrypted password constant
    private static final String ENCRYPTED_PASSWORD = "encrypted password";

    // Before each test, setup PasswordUtils
    @BeforeEach
    void setUp() {
        PasswordUtils.setup();
    }

    // After each test, tear down PasswordUtils
    @AfterEach
    void tearDown() {
    }

    // Test that the secretKey is not null after setup
    @Test
    void setupTest() {
        assertNotNull(PasswordUtils.secretKey);
    }

    // Test that encrypting a string returns an encrypted string that can be decrypted back to the original string
    @Test
    void encryptTest() {
        String encryptedString = PasswordUtils.encrypt(TEST_STRING);
        assertNotNull(encryptedString);
        assertEquals(TEST_STRING, PasswordUtils.decrypt(encryptedString));
    }

    // Test that encrypting a key throws a GeneralSecurityException
    @Test
    void encryptKeyTest() {
        assertThrows(GeneralSecurityException.class, () -> PasswordUtils.encryptKey(TEST_PASSWORD));
    }

    // Test that decrypting a string returns the original string
    @Test
    void decryptTest() {
        String encryptedString = PasswordUtils.encrypt(TEST_STRING);
        assertNotNull(encryptedString);
        assertEquals(TEST_STRING, PasswordUtils.decrypt(encryptedString));
    }

    // Test that hashing a password returns a non-null string and that it can be verified
    @Test
    void hashPasswordTest() {
        String hashedPassword = PasswordUtils.hashPassword(TEST_PASSWORD);
        assertNotNull(hashedPassword);
        assertEquals(true, PasswordUtils.verifyPassword(TEST_PASSWORD, hashedPassword));
    }

    // Test that verifying a password returns true for the correct password
    @Test
    void verifyPasswordTest() {
        String hashedPassword = PasswordUtils.hashPassword(TEST_PASSWORD);
        assertNotNull(hashedPassword);
        assertEquals(true, PasswordUtils.verifyPassword(TEST_PASSWORD, hashedPassword));
    }

    // Test that storing the key in the database works correctly
    @Test
    void storeKeyInDatabaseTest() {
        // TODO: Write a test that asserts that the key is correctly stored in the database
    }

    // Test that loading the key from the database works correctly
    @Test
    void loadKeyFromDatabaseTest() {
        // TODO: Write a test that asserts that the key is correctly loaded from the database
    }
}

