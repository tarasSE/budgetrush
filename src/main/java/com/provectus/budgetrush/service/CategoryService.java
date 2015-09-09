package com.provectus.budgetrush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService extends GenericService<Category, CategoryRepository> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    protected CategoryRepository getRepository() {
        return categoryRepository;
    }
}
