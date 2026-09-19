package com.example.datingapp.repository;

import com.example.datingapp.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateProfileRepository implements ProfileRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Profile> profileRowMapper = (rs, rowNum) -> {
        Profile profile = new Profile();
        profile.setId(rs.getLong("id"));
        profile.setName(rs.getString("name"));
        profile.setAge(rs.getInt("age"));

        String keywordsStr = rs.getString("keywords");
        if (keywordsStr != null && !keywordsStr.isEmpty()) {
            profile.setKeywords(Arrays.asList(keywordsStr.split(",")));
        }

        profile.setOpenInfo(rs.getString("open_info"));
        profile.setClosedInfo(rs.getString("closed_info"));
        return profile;
    };

    @Override
    public List<Profile> findAll() {
        return jdbcTemplate.query("SELECT * FROM profiles", profileRowMapper);
    }

    @Override
    public Optional<Profile> findById(Long id) {
        List<Profile> results = jdbcTemplate.query("SELECT * FROM profiles WHERE id = ?", profileRowMapper, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public List<Profile> findByKeyword(String keyword) {
        return jdbcTemplate.query("SELECT * FROM profiles WHERE keywords LIKE ?", profileRowMapper, "%" + keyword + "%");
    }

    @Override
    public Profile save(Profile profile) {
        if (profile.getId() == null) {
            String sql = "INSERT INTO profiles (name, age, keywords, open_info, closed_info) VALUES (?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, profile.getName());
                if (profile.getAge() != null) ps.setInt(2, profile.getAge()); else ps.setNull(2, java.sql.Types.INTEGER);
                ps.setString(3, profile.getKeywords() != null ? String.join(",", profile.getKeywords()) : "");
                ps.setString(4, profile.getOpenInfo());
                ps.setString(5, profile.getClosedInfo());
                return ps;
            }, keyHolder);

            if (keyHolder.getKeys() != null && keyHolder.getKeys().get("id") != null) {
                profile.setId(((Number) keyHolder.getKeys().get("id")).longValue());
            }
        } else {
            String sql = "UPDATE profiles SET name = ?, age = ?, keywords = ?, open_info = ?, closed_info = ? WHERE id = ?";
            jdbcTemplate.update(sql,
                    profile.getName(),
                    profile.getAge(),
                    profile.getKeywords() != null ? String.join(",", profile.getKeywords()) : "",
                    profile.getOpenInfo(),
                    profile.getClosedInfo(),
                    profile.getId()
            );
        }
        return profile;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM profiles WHERE id = ?", id);
    }
}