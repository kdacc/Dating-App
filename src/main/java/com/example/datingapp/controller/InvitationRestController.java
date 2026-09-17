package com.example.datingapp.controller;

import com.example.datingapp.model.Invitation;
import com.example.datingapp.service.InvitationService;
import com.github.fge.jsonpatch.JsonPatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invitations")
public class InvitationRestController {

    private final InvitationService invitationService;

    public InvitationRestController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @Operation(summary = "Список запрошень", description = "Повертає всі запрошення в системі")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список успішно отримано")
    })
    @GetMapping
    public ResponseEntity<?> getInvitations() {
        return ResponseEntity.ok(invitationService.getAllInvitations());
    }

    @Operation(
            summary = "Часткове оновлення запрошення (JSON Patch)",
            description = "Оновлює запрошення за допомогою набору операцій JSON Patch згідно з RFC 6902, " +
                    "наприклад для зміни статусу: [{\"op\":\"replace\",\"path\":\"/status\",\"value\":\"ACCEPTED\"}]"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Запрошення успішно оновлено"),
            @ApiResponse(responseCode = "404", description = "Запрошення не знайдено"),
            @ApiResponse(responseCode = "400", description = "Некоректний формат patch-операцій")
    })
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Invitation> patchInvitation(@PathVariable Long id, @RequestBody JsonPatch patch) {
        return invitationService.patchInvitation(id, patch)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}