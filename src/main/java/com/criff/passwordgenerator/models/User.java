package com.criff.passwordgenerator.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.sql.Timestamp;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The `User` class represents a user in the system.
 */
@Entity
@Table(name = "User")
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user.
     */
    @Column(name = "Username", nullable = false)
    @NotBlank
    private String name;

    /**
     * The email address of the user.
     */
    @Column(name = "Email", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    /**
     * The password of the user.
     */
    @Column(name = "Password", nullable = false)
    @NotBlank
    @Size(min = 8)
    private String password;

    /**
     * The timestamp of when the user was created.
     */
    @Column(name = "createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    /**
     * The timestamp of when the user was last updated.
     */
    @Column(name = "UpdatedAt", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    /**
     * A list of accounts associated with the user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    /**
     * A list of password reset requests associated with the user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PasswordResetRequest> passwordResetRequests;

    /**
     * No-arg constructor for the `User` class.
     */
    public User() {
    }

    /**
     * Returns the unique identifier for the user.
     *
     * @return the unique identifier for the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param id the unique identifier for the user
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Returns the timestamp of when the user was created.
     *
     * @return the timestamp of when the user was created
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp of when the user was created.
     *
     * @param createdAt the timestamp of when the user was created
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns the timestamp of when the user was last updated.
     *
     * @return the timestamp of when the user was last updated
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the timestamp of when the user was last updated.
     *
     * @param updatedAt the timestamp of when the user was last updated
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Returns a list of accounts associated with the user.
     *
     * @return a list of accounts associated with the user
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * /**
     * <p>
     * Sets a list of accounts associated with the user.
     *
     * @param accounts a list of accounts associated with the user
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * Returns a list of password reset requests associated with the user.
     *
     * @return a list of password reset requests associated with the user
     */
    public List<PasswordResetRequest> getPasswordResetRequests() {
        return passwordResetRequests;
    }

    /**
     * Sets a list of password reset requests associated with the user.
     *
     * @param passwordResetRequests a list of password reset requests associated with the user
     */
    public void setPasswordResetRequests(List<PasswordResetRequest> passwordResetRequests) {
        this.passwordResetRequests = passwordResetRequests;
    }

    /**
     * Verifies if the provided password matches the user's password.
     *
     * @param password the password to verify
     * @return true if the provided password matches the user's password, false otherwise
     */
    public boolean verifyPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }
}
