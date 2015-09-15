package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.Contractor;
import com.provectus.budgetrush.service.ContractorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RequestMapping
@RestController
public class ContractorController {

    @Autowired
    private ContractorService service;

    @RequestMapping(value = "/v1/contractors", method = GET, headers = "Accept=application/json")
    @ResponseBody
    public List<Contractor> listAll() {
        log.info("Get all contractors.");
        return service.getAll();
    }

    @RequestMapping(method = GET, value = "/v1/contractors/{id}")
    @ResponseBody
    public Contractor getById(@PathVariable Integer id) {
        log.info("Get contractor by id " + id);
        return service.getById(id);
    }

    @RequestMapping(value = "/v1/contractors", method = POST)
    @ResponseBody
    public Contractor create(@RequestBody Contractor contractor) {
        log.info("Create/update new contractor.");
        service.createOrUpdate(contractor);
        return contractor;
    }

    @RequestMapping(value = "/v1/contractors/{id}", method = PUT)
    @ResponseBody
    public Contractor update(@RequestBody Contractor contractor, @PathVariable Integer id) {
        log.info("Create/update contractor id " + id);
        contractor.setId(id);
        service.createOrUpdate(contractor);
        return contractor;
    }

    @RequestMapping(value = "/v1/contractors/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete contractor by id" + id);
        service.delete(id);
    }
}
