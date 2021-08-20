package com.sanutem.backend.repository;

import com.sanutem.backend.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer> {

}