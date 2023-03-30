package com.autentia.repository;


import com.autentia.model.Group;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;


@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
