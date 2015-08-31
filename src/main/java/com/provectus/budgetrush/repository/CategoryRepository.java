package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.provectus.budgetrush.data.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findByName(String name);
}
