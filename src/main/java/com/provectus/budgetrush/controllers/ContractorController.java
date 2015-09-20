package com.provectus.budgetrush.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.provectus.budgetrush.data.Contractor;
import com.provectus.budgetrush.service.ContractorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "/v1/contractors", headers = "Accept=application/json")
@RestController
public class ContractorController {

    @Autowired
    private ContractorService service;

    @RequestMapping(method = GET)
    @ResponseBody
    public List<Contractor> listAll() {
        log.info("Get all contractors.");
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Contractor getById(@PathVariable Integer id) {
        log.info("Get contractor by id " + id);
        return service.getById(id);
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public Contractor create(@RequestBody Contractor contractor) {
        log.info("Create/update new contractor.");
        contractor.setId(0);
        service.createOrUpdate(contractor);
        return contractor;
    }

    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Contractor update(@RequestBody Contractor contractor, @PathVariable Integer id) {
        log.info("Create/update contractor id " + id);
        contractor.setId(id);
        service.createOrUpdate(contractor);
        return contractor;
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete contractor by id" + id);
        service.delete(id);
    }
}
