package com.criff.passwordgenerator.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Password_reset_requests")
public class PasswordResetRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Token", nullable = false, unique = true)
    private String token;

    @Column(name = "Expiry_time", nullable = false)
    private Timestamp expiryTime;

    @ManyToOne
    @JoinColumn(name = "User_id", nullable = false)
    private User user;

// Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase().trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token.trim();
    }

    public Timestamp getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Timestamp expiryTime) {
        this.expiryTime = expiryTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

// Other methods

    @PrePersist
    public void prepareDataForPersistence() {
        setEmail(email);
        setToken(token);
    }
}

