package com.example.datingapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "invitations")
public class Invitation {
    public enum Status { PENDING, ACCEPTED, REJECTED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Profile sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Profile receiver;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Invitation() {}

    public Invitation(Long id, Profile sender, Profile receiver, Status status) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Profile getSender() { return sender; }
    public void setSender(Profile sender) { this.sender = sender; }

    public Profile getReceiver() { return receiver; }
    public void setReceiver(Profile receiver) { this.receiver = receiver; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Long getSenderId() {
        return sender != null ? sender.getId() : null;
    }

    public Long getReceiverId() {
        return receiver != null ? receiver.getId() : null;
    }
}