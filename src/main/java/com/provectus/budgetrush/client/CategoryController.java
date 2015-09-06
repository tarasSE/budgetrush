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

    @RequestMapping(value = "/category", method = GET)
    public
    @ResponseBody
    List listAll() {
        return service.getAll();
    }

    /*Does not work with categories that have a parent because that parent is written into JSON fully , not as ID*/
    @RequestMapping(method = GET, value = "/category/{id}")
    public
    @ResponseBody
    Category getById(@PathVariable Integer id) {

        return service.getById(id);
    }

    @RequestMapping(value = "/category", method = POST)
    public
    @ResponseBody
    Category create(@RequestBody Category category) {
        service.createAndUpdate(category);
        return category;
    }

    @RequestMapping(value = "category/{id}", method = DELETE)
    public
    @ResponseBody
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }
    /*@RequestMapping(method=RequestMethod.POST, value="/emp")
public @ResponseBody Employee addEmp(@RequestBody Employee e) {
employeeDS.add(e);
return e;
}

@RequestMapping(method=RequestMethod.PUT, value="/emp/{id}")
public @ResponseBody Employee updateEmp(
	@RequestBody Employee e, @PathVariable String id) {
employeeDS.update(e);
return e;
}

@RequestMapping(method=RequestMethod.DELETE, value="/emp/{id}")
public @ResponseBody void removeEmp(@PathVariable String id) {
employeeDS.remove(Long.parseLong(id));
}*/
}