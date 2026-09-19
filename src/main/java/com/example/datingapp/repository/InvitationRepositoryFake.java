package com.example.datingapp.repository;

import com.example.datingapp.model.Invitation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//@Repository
public class InvitationRepositoryFake implements InvitationRepository {

    private final List<Invitation> invitations = new ArrayList<>(List.of(
            new Invitation(1L, 1L, 2L, Invitation.Status.PENDING)
    ));

    @Override
    public List<Invitation> findAll() {
        return invitations;
    }

    @Override
    public void save(Invitation invitation) {
        invitations.add(invitation);
    }

    @Override
    public Optional<Invitation> findById(Long id) {
        return invitations.stream()
                .filter(inv -> inv.getId().equals(id))
                .findFirst();
    }
}