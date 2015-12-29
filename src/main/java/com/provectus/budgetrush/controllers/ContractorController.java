package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.contractor.Contractor;
import com.provectus.budgetrush.service.ContractorService;
import com.provectus.budgetrush.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RequestMapping(value = "/v1/contractors", headers = "Accept=application/json")
@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class ContractorController {

    @Autowired
    private ContractorService contractorService;

    @Autowired
    private UserService userService;

    @PostFilter("isObjectOwnerOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Contractor> listAll() {
        log.info("Get all contractors.");
        return contractorService.getAll();
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public Contractor getById(@PathVariable Integer id) {
        log.info("Get contractor by id " + id);
        return contractorService.getById(id);
    }

    @PreAuthorize("isObjectOwnerOrAdmin(@userService.getById(#contractor.getUser().getId()), 'write')")
    @RequestMapping(method = POST)
    @ResponseBody
    public Contractor create(@RequestBody Contractor contractor) {
        log.info("Create/update new contractor.");

        return contractorService.create(contractor);
    }

    @PreAuthorize("isObjectOwnerOrAdmin(@contractorService.getById(#id), 'write')")
    @RequestMapping(value = "/{id}", method = PUT)
    @ResponseBody
    public Contractor update(@RequestBody Contractor contractor, @PathVariable Integer id) {
        log.info("Create/update contractor id " + id);

        return contractorService.update(contractor, id);
    }

    @PreAuthorize("isObjectOwnerOrAdmin(@contractorService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete contractor by id" + id);
        contractorService.delete(id);
    }
}
