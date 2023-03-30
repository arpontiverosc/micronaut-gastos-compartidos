package com.autentia.repository;

import com.autentia.model.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface UserOldRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

}
