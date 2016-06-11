package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.category.Category;
import com.provectus.budgetrush.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import javax.inject.Inject;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/v1/categories", headers = "Accept=application/json")
@RestController
public class CategoryController {

    @Inject
    private CategoryService categoryService;

    @PostFilter("isObjectOwnerOrAdminOrAll(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Category> listAll() {
        log.info("Get all categories");
        return categoryService.getAll();
    }

    @PostAuthorize("isObjectOwnerOrAdminOrAll(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Category getById(@PathVariable Integer id) {
        log.info("Get category by id " + id);
        return categoryService.getById(id);
    }

    @PreAuthorize("isObjectOwnerOrAdmin(#category, 'write')")
    @RequestMapping(method = POST)
    @ResponseBody
    public Category create(@RequestBody Category category) {
        log.info("Create category");

        return categoryService.create(category);
    }

    @PreAuthorize("isObjectOwnerOrAdminForUpdateAndDelete(#category, 'write')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Category update(@RequestBody Category category, @PathVariable Integer id) {
        log.info("Update category id " + id);

        return categoryService.update(category, id);
    }

    @PreAuthorize("isObjectOwnerOrAdminForUpdateAndDelete(@categoryService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete category by id " + id);
        categoryService.delete(id);
    }

}
