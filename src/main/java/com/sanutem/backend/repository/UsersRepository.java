package com.sanutem.backend.repository;

import com.sanutem.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

}