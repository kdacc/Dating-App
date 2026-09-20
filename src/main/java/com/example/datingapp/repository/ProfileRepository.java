package com.example.datingapp.repository;

import com.example.datingapp.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
    List<Profile> findByAgeGreaterThanEqual(Integer age);

    @Query("SELECT p FROM Profile p WHERE p.openInfo LIKE %:info%")
    List<Profile> searchByOpenInfo(@Param("info") String info);

    List<Profile> findByKeywordNamed(@Param("keyword") String keyword);
}