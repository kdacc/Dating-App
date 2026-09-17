package com.example.datingapp.controller;

import com.example.datingapp.model.Profile;
import com.example.datingapp.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profiles")
public class ProfileRestController {

    private final ProfileService profileService;

    public ProfileRestController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Operation(summary = "Список анкет", description = "Повертає анкети з підтримкою фільтрації за ключовим словом, віком та пагінацією")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список успішно отримано")
    })
    @GetMapping
    public ResponseEntity<List<Profile>> getProfiles(
            @Parameter(description = "Ключове слово для фільтрації") @RequestParam(required = false) String keyword,
            @Parameter(description = "Мінімальний вік") @RequestParam(required = false) Integer minAge,
            @Parameter(description = "Максимальний вік") @RequestParam(required = false) Integer maxAge,
            @Parameter(description = "Номер сторінки (з 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Розмір сторінки") @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(profileService.getProfilesFiltered(keyword, minAge, maxAge, page, size));
    }

    @Operation(summary = "Отримати анкету за id", description = "Повертає одну анкету за її ідентифікатором")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Анкету знайдено"),
            @ApiResponse(responseCode = "404", description = "Анкету не знайдено")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        return profileService.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Створити анкету", description = "Створює нову анкету користувача")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Анкету створено")
    })
    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        Profile created = profileService.createProfile(profile);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Оновити анкету повністю", description = "Повністю замінює дані анкети за id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Анкету оновлено"),
            @ApiResponse(responseCode = "404", description = "Анкету не знайдено")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        return profileService.updateProfile(id, profile)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Часткове оновлення анкети", description = "Оновлює лише передані поля анкети (JSON Merge Patch, RFC 7386)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Анкету частково оновлено"),
            @ApiResponse(responseCode = "404", description = "Анкету не знайдено")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Profile> patchProfile(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return profileService.patchProfile(id, updates)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Видалити анкету", description = "Видаляє анкету за id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Анкету видалено"),
            @ApiResponse(responseCode = "404", description = "Анкету не знайдено")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        return profileService.deleteProfile(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}