package com.example.datingapp.repository;

import com.example.datingapp.model.Invitation;

import java.util.List;
import java.util.Optional;

public interface InvitationRepository {
    List<Invitation> findAll();
    Optional<Invitation> findById(Long id);
    void save(Invitation invitation);
}