package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.provectus.budgetrush.data.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}