package com.example.datingapp.model;

public class Invitation {
    public enum Status { PENDING, ACCEPTED, REJECTED }

    private Long id;
    private Long senderId;
    private Long receiverId;
    private Status status;

    public Invitation() {}

    public Invitation(Long id, Long senderId, Long receiverId, Status status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSenderId() { return senderId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }

    public Long getReceiverId() { return receiverId; }
    public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}