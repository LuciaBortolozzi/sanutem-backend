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

    @Modifying
    @Query("UPDATE Users u SET u.email = ?1, u.firstName = ?2, u.lastName = ?3, u.sex = ?4 WHERE u.username = ?5")
    void updateUserByUsername(String email, String firstName, String lastName, String sex, String username);

    @Query("SELECT u.username FROM Users u WHERE u.id = ?1")
    String findUsernameByID(Integer idRecep);

    @Query("SELECT u.id FROM Users u WHERE u.username = ?1")
    Integer findIDByUsername(String username);

    @Query("SELECT u FROM Users u WHERE u.specialization = ?1 AND u.province = ?2 AND u.healthInsurances = ?3")
    Users[] findProfessionalBySpecializationAndProvinceAndHealthInsurance(String specialization, String province, String healthInsurance);
}
