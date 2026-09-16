package com.example.datingapp.repository;

import com.example.datingapp.model.Invitation;

import java.util.List;

public interface InvitationRepository {
    List<Invitation> findAll();
    void save(Invitation invitation);
}