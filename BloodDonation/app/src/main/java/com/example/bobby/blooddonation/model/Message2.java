package com.example.bobby.blooddonation.model;

import java.io.Serializable;

public class Message2 implements Serializable {
    String id, message, createdAt;
    User2 user;

    public Message2() {
    }

    public Message2(String id, String message, String createdAt, User2 user) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User2 getUser() {
        return user;
    }

    public void setUser(User2 user) {
        this.user = user;
    }
}
