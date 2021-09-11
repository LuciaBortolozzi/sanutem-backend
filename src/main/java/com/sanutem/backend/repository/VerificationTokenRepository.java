package com.sanutem.backend.repository;

import com.sanutem.backend.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("DELETE FROM VerificationToken v WHERE v.id = ?1")
    void deleteTokenByIdToken(Long idToken);

    @Query("SELECT t.id FROM VerificationToken t WHERE t.user.username  = ?1")
    Integer findIdTokenByUsername(String nameUser);
}