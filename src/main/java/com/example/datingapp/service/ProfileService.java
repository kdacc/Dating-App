package com.example.datingapp.service;

import com.example.datingapp.model.Profile;
import com.example.datingapp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired // ін'єкція через конструктор
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public List<Profile> searchByKeyword(String keyword) {
        return profileRepository.findByKeyword(keyword);
    }
}