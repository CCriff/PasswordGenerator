package com.criff.passwordgenerator.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * The PasswordUtils class provides functionality to securely handle passwords.
 * It includes methods to encrypt, decrypt, hash and verify passwords.
 */
public class PasswordUtils {
    // AES algorithm string constant
    private static final String AES_ALGORITHM = "AES";

    // Secret key generated using generateKey() method
    private static final String SECRET_KEY = generateKey();

    // Algorithm constant
    private static final String ALGORITHM = "AES";

    // Key generated using generateKey() method
    private static final byte[] KEY = generateKey().getBytes();

    // Secret key specification
    static SecretKeySpec secretKey;

    // BCrypt password encoder
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final String DB_URL = "jdbc:mysql://localhost:3306/key_store";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    /**
     * The setup method sets up the secretKey specification.
     */
    public static void setup() {
        // Decode the SECRET_KEY and set it to secretKey
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        secretKey = new SecretKeySpec(decodedKey, AES_ALGORITHM);
    }

    /**
     * The encrypt method encrypts the given string using AES algorithm.
     *
     * @param strToEncrypt the string to be encrypted
     * @return the encrypted string
     */
    public static String encrypt(String strToEncrypt) {
        try {
            // Create a cipher using AES algorithm
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            // Initialize the cipher in encryption mode using secretKey
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            // Return the encrypted string as base64 encoded string
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /**
     * The encryptKey method encrypts the given password using AES algorithm.
     *
     * @param password the password to be encrypted
     * @return the encrypted password
     * @throws Exception in case of any error during encryption
     */
    public static String encryptKey(String password) throws Exception {
        // Create a key using the KEY and ALGORITHM
        Key key = new SecretKeySpec(KEY, ALGORITHM);
        // Create a cipher using the ALGORITHM
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // Initialize the cipher in encryption mode using key
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // Return the encrypted password as a base64 encoded string
        return Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * The decrypt method decrypts the given string using AES algorithm.
     *
     * @param strToDecrypt the string to be decrypted
     * @return the decrypted string
     */
    public static String decrypt(String strToDecrypt) {
        try {
            // Create a cipher using AES algorithm
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            // Initialize the cipher in decryption mode using secretKey
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            // Return the decrypted string as a UTF-8 encoded string
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    /**
     * The decryptKey method decrypts the given encrypted password using AES algorithm.
     *
     * @param encryptedPassword the encrypted password to be decrypted
     * @return the decrypted password
     * @throws Exception in case of any error during decryption
     */
    public static String decryptKey(String encryptedPassword) throws Exception {
        // Create a key using the KEY and ALGORITHM
        Key key = new SecretKeySpec(KEY, ALGORITHM);
        // Create a cipher using the ALGORITHM
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // Initialize the cipher in decryption mode using key
        cipher.init(Cipher.DECRYPT_MODE, key);
        // Return the decrypted password as a UTF-8 encoded string
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedPassword)), StandardCharsets.UTF_8);
    }

    /**
     * The hashPassword method hashes the given password using BCrypt algorithm.
     *
     * @param password the password to be hashed
     * @return the hashed password
     */
    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * The verifyPassword method verifies the given password against the hashed password.
     *
     * @param password       the password to be verified
     * @param hashedPassword the hashed password
     * @return true if the password is verified, false otherwise
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }

    /**
     * The generateKey method generates a random secret key for AES algorithm.
     *
     * @return the generated secret key as a base64 encoded string
     */
    private static String generateKey() {
        try {
            // Generate a secure random number
            SecureRandom secureRandom = new SecureRandom();
            // Create a byte array of size 16 to store the random generated key
            byte[] key = new byte[16];
            // Fill the byte array with random bytes
            secureRandom.nextBytes(key);
            // Return the generated key as a base64 encoded string
            return Base64.getEncoder().encodeToString(key);
        } catch (Exception e) {
            System.out.println("Error while generating secret key: " + e.toString());
        }
        return null;
    }

    /**
     * The storeKey method stores the given key in the database.
     *
     * @param key the key to be stored
     * @return true if the key is stored successfully, false otherwise
     */
    public static boolean storeKeyInDatabase(String key) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO keys (key) VALUES (?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, key);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error while storing secret key: " + e.toString());
        }
        return false;
    }

    /**
     * The loadKey method loads the key from the database.
     *
     * @return the loaded key as a string
     */
    public static String loadKeyFromDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT key FROM keys LIMIT 1";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("key");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while loading secret key: " + e.toString());
        }
        return null;
    }

    /**
     * The storeKey method stores the given key in a file.
     *
     * @param key the key to be stored
     * @return true if the key is stored successfully, false otherwise
     */
    private static boolean storeKeyToFile(String key) {
        try {
            // Create a new file
            File file = new File("KEY_FILE_NAME");
            // Create a file writer
            FileWriter fileWriter = new FileWriter(file);
            // Write the key to the file
            fileWriter.write(key);
            // Close the file writer
            fileWriter.close();
            // Return true if the key is stored successfully
            return true;
        } catch (Exception e) {
            System.out.println("Error while storing secret key: " + e.toString());
        }
        // Return false if the key is not stored successfully
        return false;
    }

    /**
     * The loadKey method loads the key from the file.
     *
     * @return the loaded key as a string
     */
    private static String loadKeyFromFile() {
        try {
            // Create a new file
            File file = new File("KEY_FILE_NAME");
            // Create a file reader
            FileReader fileReader = new FileReader(file);
            // Read the key from the file
            StringBuilder keyBuilder = new StringBuilder();
            int c;
            while ((c = fileReader.read()) != -1) {
                keyBuilder.append((char) c);
            }
            // Close the file reader
            fileReader.close();
            // Return the loaded key as a string
            return keyBuilder.toString();
        } catch (Exception e) {
            System.out.println("Error while loading secret key: " + e.toString());
        }
        // Return null if the key is not loaded successfully
        return null;
    }
}




