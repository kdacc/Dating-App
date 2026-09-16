package com.example.datingapp.repository;

import com.example.datingapp.model.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRepositoryFake implements ProfileRepository {

    private final List<Profile> profiles = new ArrayList<>(List.of(
            new Profile(1L, "Олена", 25, List.of("подорожі", "книги"),
                    "Любить читати", "Шукає серйозні стосунки"),
            new Profile(2L, "Ігор", 28, List.of("спорт", "музика"),
                    "Займається бігом", "Відкритий до знайомств")
    ));

    @Override
    public List<Profile> findAll() {
        return profiles;
    }

    @Override
    public Optional<Profile> findById(Long id) {
        return profiles.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Profile> findByKeyword(String keyword) {
        return profiles.stream()
                .filter(p -> p.getKeywords().contains(keyword))
                .toList();
    }
}