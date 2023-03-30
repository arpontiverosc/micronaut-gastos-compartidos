package com.autentia.repository;


import com.autentia.model.User;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;


import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
