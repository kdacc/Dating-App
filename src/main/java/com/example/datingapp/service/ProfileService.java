package com.example.datingapp.service;

import com.example.datingapp.model.Profile;
import com.example.datingapp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public List<Profile> searchByKeyword(String keyword) {
        return profileRepository.findByKeyword(keyword);
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile createProfile(Profile profile) {
        profile.setId(null);
        return profileRepository.save(profile);
    }

    public Optional<Profile> updateProfile(Long id, Profile updated) {
        return profileRepository.findById(id).map(existing -> {
            updated.setId(id);
            return profileRepository.save(updated);
        });
    }

    public boolean deleteProfile(Long id) {
        if (profileRepository.findById(id).isEmpty()) {
            return false;
        }
        profileRepository.deleteById(id);
        return true;
    }

    public Optional<Profile> patchProfile(Long id, Map<String, Object> updates) {
        return profileRepository.findById(id).map(profile -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name" -> profile.setName((String) value);
                    case "age" -> profile.setAge((Integer) value);
                    case "openInfo" -> profile.setOpenInfo((String) value);
                    case "closedInfo" -> profile.setClosedInfo((String) value);
                }
            });
            return profileRepository.save(profile);
        });
    }

    public List<Profile> getProfilesFiltered(String keyword, Integer minAge, Integer maxAge, int page, int size) {
        List<Profile> filtered = profileRepository.findAll().stream()
                .filter(p -> keyword == null || p.getKeywords().contains(keyword))
                .filter(p -> minAge == null || p.getAge() >= minAge)
                .filter(p -> maxAge == null || p.getAge() <= maxAge)
                .toList();

        int from = Math.min(page * size, filtered.size());
        int to = Math.min(from + size, filtered.size());
        return filtered.subList(from, to);
    }
}