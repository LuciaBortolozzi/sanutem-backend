package com.sanutem.backend.repository;

import com.sanutem.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    @Transactional
    @Modifying
    @Query("DELETE FROM Users u WHERE u.id = ?1")
    void deleteUserById(Integer idUser);
}