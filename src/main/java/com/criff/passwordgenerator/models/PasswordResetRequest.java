package com.criff.passwordgenerator.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Password_reset_requests")
public class PasswordResetRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Token", nullable = false)
    private String token;

    @Column(name = "Expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Used", nullable = false)
    private RequestStatus used;

    @Column(name = "Created_at", nullable = false)
    private LocalDateTime createdAt;

    public PasswordResetRequest() {
    }

    public PasswordResetRequest(String email, String token, LocalDateTime expirationDate) {
        this.email = email;
        this.token = token;
        this.expirationDate = expirationDate;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

    public boolean isValid() {
        return !isExpired() && used == RequestStatus.UNUSED;
    }

    public void markAsUsed() {
        this.used = RequestStatus.USED;
    }

    // getters

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public RequestStatus getUsed() {
        return used;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // setters

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setUsed(RequestStatus used) {
        this.used = used;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
