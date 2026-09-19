package com.example.datingapp.repository;

import com.example.datingapp.model.Invitation;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientInvitationRepository implements InvitationRepository {

    private final JdbcClient jdbcClient;

    public JdbcClientInvitationRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Invitation> findAll() {
        return jdbcClient.sql("SELECT * FROM invitations")
                .query(Invitation.class)
                .list();
    }

    @Override
    public Optional<Invitation> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM invitations WHERE id = :id")
                .param("id", id)
                .query(Invitation.class)
                .optional();
    }

    @Override
    public void save(Invitation invitation) {
        if (invitation.getId() == null) {
            String sql = "INSERT INTO invitations (sender_id, receiver_id, status) VALUES (:senderId, :receiverId, :status) RETURNING id";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcClient.sql(sql)
                    .param("senderId", invitation.getSenderId())
                    .param("receiverId", invitation.getReceiverId())
                    .param("status", invitation.getStatus().name())
                    .update(keyHolder);

            if (keyHolder.getKeys() != null && keyHolder.getKeys().get("id") != null) {
                invitation.setId(((Number) keyHolder.getKeys().get("id")).longValue());
            }
        } else {
            String sql = "UPDATE invitations SET sender_id = :senderId, receiver_id = :receiverId, status = :status WHERE id = :id";
            jdbcClient.sql(sql)
                    .param("senderId", invitation.getSenderId())
                    .param("receiverId", invitation.getReceiverId())
                    .param("status", invitation.getStatus().name())
                    .param("id", invitation.getId())
                    .update();
        }
    }
}