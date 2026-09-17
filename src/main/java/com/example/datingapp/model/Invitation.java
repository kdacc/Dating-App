package com.example.datingapp.model;

public class Invitation {
    public enum Status { PENDING, ACCEPTED, REJECTED }

    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private Status status;

    public Invitation(Long id, Long fromUserId, Long toUserId, Status status) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFromUserId() { return fromUserId; }
    public void setFromUserId(Long fromUserId) { this.fromUserId = fromUserId; }

    public Long getToUserId() { return toUserId; }
    public void setToUserId(Long toUserId) { this.toUserId = toUserId; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}