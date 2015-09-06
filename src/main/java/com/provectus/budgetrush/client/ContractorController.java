package com.provectus.budgetrush.client;

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

    @RequestMapping(value = "/contractor", method = GET, headers = "Accept=application/json")
    @ResponseBody
    public List<Contractor> listAll() {
        return service.getAll();
    }

    @RequestMapping(method = GET, value = "/contractor/{id}")
    @ResponseBody
    public Contractor getById(@PathVariable Integer id) {

        return service.getById(id);
    }

    @RequestMapping(value = "/contractor", method = POST)
    @ResponseBody
    public Contractor create(@RequestBody Contractor contractor) {
        service.createAndUpdate(contractor);
        return contractor;
    }

    @RequestMapping(value = "contractor/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
