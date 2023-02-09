package com.criff.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

    private static final String AES_ALGORITHM = "AES";
    private static final String SECRET_KEY = "$2a$10$RANDOM_KEY_HERE_AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";

    private static SecretKeySpec secretKey;
    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void setup() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        secretKey = new SecretKeySpec(decodedKey, AES_ALGORITHM);
    }

    public static String encrypt(String strToEncrypt) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static String generateSecurePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }

    public static String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        byte[] password = new byte[16];
        random.nextBytes(password);
        return new String(password);
    }
}

