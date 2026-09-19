package com.example.datingapp.service;

import com.example.datingapp.model.Invitation;
import com.example.datingapp.model.Profile;
import com.example.datingapp.repository.InvitationRepository;
import com.example.datingapp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

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
        notificationHelper.notify("Нове запрошення від " + invitation.getSenderId());
    }

    public Optional<Invitation> patchInvitation(Long id, JsonPatch patch) {
        return invitationRepository.findById(id).map(invitation -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode patched = patch.apply(mapper.convertValue(invitation, JsonNode.class));
                Invitation updated = mapper.treeToValue(patched, Invitation.class);
                invitationRepository.save(updated);
                return updated;
            } catch (JsonPatchException | com.fasterxml.jackson.core.JsonProcessingException e) {
                throw new RuntimeException("Не вдалося застосувати patch", e);
            }
        });
    }

    @Transactional
    public void acceptInvitation(Long invitationId, boolean simulateError) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new RuntimeException("Запрошення не знайдено"));

        invitation.setStatus(Invitation.Status.ACCEPTED);
        invitationRepository.save(invitation);

        if (simulateError) {
            throw new RuntimeException("Штучна помилка! Транзакція має відкотитися.");
        }

        Profile receiver = profileRepository.findById(invitation.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Профіль отримувача не знайдено"));

        String currentClosedInfo = receiver.getClosedInfo() == null ? "" : receiver.getClosedInfo();
        receiver.setClosedInfo(currentClosedInfo + " | Має новий підтверджений зв'язок");
        profileRepository.save(receiver);
    }
}