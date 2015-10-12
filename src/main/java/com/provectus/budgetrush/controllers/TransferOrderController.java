package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.TransferOrder;
import com.provectus.budgetrush.service.TransferOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RequestMapping(value = "/v1/transfers", headers = "Accept=application/json")
@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class TransferOrderController {

    @Autowired
    private TransferOrderService service;

    @PostFilter("isObjectOwnerOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List getTransfers() {
        log.info("Get all orders");
        return service.getAll();
    }

    @PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public TransferOrder getTransfer(@PathVariable int id) {
        return service.getById(id);
    }

    @PreAuthorize("isObjectOwnerOrAdmin(#order, 'write')")
    @RequestMapping(value = "/transfer", method = POST)
    @ResponseBody
    public TransferOrder transfer(@RequestBody TransferOrder transfer) {
        log.info("Create/update new order");

        return service.getById(service.transfer(transfer).getId());
    }

    @PreAuthorize("isObjectOwnerOrAdmin(@transferOrderService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete order by id" + id);
        service.delete(id);
    }
}
