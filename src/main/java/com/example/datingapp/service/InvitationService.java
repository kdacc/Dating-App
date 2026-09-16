package com.example.datingapp.service;

import com.example.datingapp.model.Invitation;
import com.example.datingapp.repository.InvitationRepository;
import com.example.datingapp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;

    @Autowired // напряму в поле
    private ProfileRepository profileRepository;

    private NotificationHelper notificationHelper;

    public InvitationService(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @Autowired // через сетер
    public void setNotificationHelper(NotificationHelper notificationHelper) {
        this.notificationHelper = notificationHelper;
    }

    public List<Invitation> getAllInvitations() {
        return invitationRepository.findAll();
    }

    public void sendInvitation(Invitation invitation) {
        invitationRepository.save(invitation);
        notificationHelper.notify("Нове запрошення від " + invitation.getFromUserId());
    }
}