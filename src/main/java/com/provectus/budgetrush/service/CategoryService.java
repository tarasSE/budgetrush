package com.provectus.budgetrush.service;

import com.provectus.budgetrush.data.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);

    void delete(int id);

    Category getByName(String name);

    Category getByID(int id);

    Category editCategory(Category category);

    List<Category> getAll();

    void changeParent(Integer childId, Integer parentId);

}
