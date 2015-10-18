package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.repository.CategoryRepository;

@Service
@Repository
public class CategoryService extends GenericService<Category, CategoryRepository> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    protected CategoryRepository getRepository() {
        return categoryRepository;
    }
}
