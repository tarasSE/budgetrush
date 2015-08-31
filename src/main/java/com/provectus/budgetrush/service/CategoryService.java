package com.provectus.budgetrush.service;

import java.util.List;

import com.provectus.budgetrush.data.Category;

public interface CategoryService {

    Category addCategory(Category category);

    void delete(int id);

    Category getByName(String name);

    Category getByID(int id);

    Category editCategory(Category category);

    List<Category> getAll();

}
