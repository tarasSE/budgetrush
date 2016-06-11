package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.category.Category;
import com.provectus.budgetrush.service.CategoryService;
import com.provectus.budgetrush.service.GroupService;
import com.provectus.budgetrush.service.UserService;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

@ContextConfiguration(classes = {
        CategoryService.class,
        UserService.class,
        GroupService.class
})

public class CategoriesTest extends TestGenericService<Category, CategoryService> {
    @Inject
    private CategoryService categoryService;
    @Inject
    private UserService userService;

    @Override
    protected Category getEntity() {
        Category category = new Category();

        category.setName(Integer.toString(random.nextInt()));
        category.setParent(null);
        category.setUser(userService.getById(1));

        return category;
    }

    protected CategoryService getService() {
        return categoryService;
    }

    @Override
    public void delete() {
        //do nothing
    }
}
