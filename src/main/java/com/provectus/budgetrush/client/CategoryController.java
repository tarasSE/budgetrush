package com.provectus.budgetrush.client;

import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RequestMapping
@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @RequestMapping(value = "/category", method = GET, headers = "Accept=application/json")
    @ResponseBody
    public List<Category> listAll() {
        log.info("Get all categories");
        return service.getAll();
    }

    @RequestMapping(method = GET, value = "/category/{id}")
    @ResponseBody
    public Category getById(@PathVariable Integer id) {
        log.info("Get category by id " + id);
        return service.getById(id);
    }

    @RequestMapping(value = "/category", method = POST)
    @ResponseBody
    public Category create(@RequestBody Category category) {
        log.info("Create/update category");
        service.createAndUpdate(category);
        return category;
    }

    @RequestMapping(value = "category/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete category by id " + id);
        service.delete(id);
    }

}
