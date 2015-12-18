package com.provectus.budgetrush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.category.Category;

@Repository
@Transactional(readOnly = true)
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
