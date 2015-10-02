package com.provectus.budgetrush.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/v1/categories", headers = "Accept=application/json")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostFilter("isObjectOwnerOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Category> listAll() {
        log.info("Get all categories");
        return service.getAll();
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Category getById(@PathVariable Integer id) {
        log.info("Get category by id " + id);
        return service.getById(id);
    }

    @PreAuthorize("isObjectOwnerOrAdmin(#category, 'write')")
    @RequestMapping(method = POST)
    @ResponseBody
    public Category create(@RequestBody Category category) {
        log.info("Create/update category");
        service.create(category);
        return category;
    }

    @PreAuthorize("isObjectOwnerOrAdmin(#category, 'write')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Category update(@RequestBody Category category, @PathVariable Integer id) {
        log.info("Create/update category id " + id);
        service.update(category, id);
        return category;
    }

    @PreAuthorize("isObjectOwnerOrAdmin(@categoryService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete category by id " + id);
        service.delete(id);
    }

}
