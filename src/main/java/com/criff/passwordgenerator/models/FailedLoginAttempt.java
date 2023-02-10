package com.criff.passwordgenerator.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Failed_login_attempts")
public class FailedLoginAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountUsername;
    private int failedAttempts;
    private LocalDateTime createdAt;

    public FailedLoginAttempt() {
    }

    public FailedLoginAttempt(String accountUsername, int failedAttempts, LocalDateTime createdAt) {
        this.accountUsername = accountUsername;
        this.failedAttempts = failedAttempts;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

