package com.criff.passwordgenerator.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "History")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Old_hashed_password")
    private String oldHashedPassword;

    @Column(name = "New_hashed_password")
    private String newHashedPassword;

    @Column(name = "Created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Account_id")
    private Account account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOldHashedPassword() {
        return oldHashedPassword;
    }

    public void setOldHashedPassword(String oldHashedPassword) {
        this.oldHashedPassword = oldHashedPassword;
    }

    public String getNewHashedPassword() {
        return newHashedPassword;
    }

    public void setNewHashedPassword(String newHashedPassword) {
        this.newHashedPassword = newHashedPassword;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}


