package com.example.datingapp.repository;

import com.example.datingapp.model.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    List<Profile> findAll();
    Optional<Profile> findById(Long id);
    List<Profile> findByKeyword(String keyword);
    Profile save(Profile profile);
    void deleteById(Long id);
}