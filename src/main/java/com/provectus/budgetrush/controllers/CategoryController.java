package com.provectus.budgetrush.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/v1/categories", headers = "Accept=application/json")
@Controller
public class CategoryController {

    @Autowired
    private CategoryService service;

    @RequestMapping(method = GET)
    @ResponseBody
    public List<Category> listAll() {
        log.info("Get all categories");
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Category getById(@PathVariable Integer id) {
        log.info("Get category by id " + id);
        return service.getById(id);
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public Category create(@RequestBody Category category) {
        log.info("Create/update category");
        category.setId(0);
        service.createOrUpdate(category);
        return category;
    }

    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Category update(@RequestBody Category category, @PathVariable Integer id) {
        log.info("Create/update category id " + id);
        category.setId(id);
        service.createOrUpdate(category);
        return category;
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete category by id " + id);
        service.delete(id);
    }

}
