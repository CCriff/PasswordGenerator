package com.criff.passwordgenerator.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Failed_login_attempts")
public class FailedLoginAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The username of the account associated with the failed login attempt
    @Column(name = "Account_username", nullable = false)
    private String username;

    // The timestamp of the failed login attempt
    @Column(name = "Failed_attempt_timestamp", nullable = false)
    private Timestamp attemptTime;

    // Getter and setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getAttemptTime() {
        return attemptTime;
    }

    public void setAttemptTime(Timestamp attemptTime) {
        this.attemptTime = attemptTime;
    }
}
