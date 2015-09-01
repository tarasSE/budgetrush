package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CategoryServiceBean implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Category addCategory(Category category) {
        log.info("Start to save category " + category.getName());
        return categoryRepository.saveAndFlush(category);
    }


    public void delete(int id) {
        log.info("Start to delete category (ID=" + id + ")");
        categoryRepository.delete(id);
    }


    public Category getByID(int id) {
        return categoryRepository.getOne(id);
    }


    public Category editCategory(Category category) {
        return categoryRepository.saveAndFlush(category);
    }


    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void changeParent(Integer childId, Integer parentId) {
        Category category = categoryRepository.getOne(childId);
        category.setParent(parentId);
        categoryRepository.saveAndFlush(category);
    }


    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }

}
