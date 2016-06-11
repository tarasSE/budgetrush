package com.provectus.budgetrush.service;

import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.category.Category;
import com.provectus.budgetrush.repository.CategoryRepository;

@Service
@Repository
@Transactional(readOnly = true)
public class CategoryService extends GenericService<Category, CategoryRepository> {

    @Inject
    private CategoryRepository categoryRepository;

    @Override
    protected CategoryRepository getRepository() {
        return categoryRepository;
    }
}
