package com.example.datingapp.repository;

import com.example.datingapp.model.Invitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation, Long> {
}