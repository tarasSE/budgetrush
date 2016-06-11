package com.provectus.budgetrush.controllers;

import com.provectus.budgetrush.data.order.TransferOrder;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.GroupService;
import com.provectus.budgetrush.service.TransferOrderService;
import lombok.extern.slf4j.Slf4j;
import javax.inject.Inject;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RequestMapping(value = "/v1/transfers", headers = "Accept=application/json")
@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class TransferOrderController {

    @Inject
    private TransferOrderService transferOrderService;

    @Inject
    private GroupService groupService;

    @Inject
    private AccountService accountService;

    @PostFilter("inGroupOrAdmin(filterObject, 'read')")
    @RequestMapping(method = GET)
    @ResponseBody
    public List<TransferOrder> getTransfers() {
        log.info("Get all orders");
        return transferOrderService.getAll();
    }

    @PostAuthorize("inGroupOrAdmin(returnObject, 'read')")
    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public TransferOrder getOne(@PathVariable int id) {
        return transferOrderService.getById(id);
    }

    @PreAuthorize("inGroupOrAdmin(@groupService.getById(" +
            "@accountService.getById(" +
            "#transfer.getAccount().getId()" +
            ").getGroup().getId()), 'write')")
    @RequestMapping(method = POST)
    @ResponseBody
    public TransferOrder transfer(@RequestBody TransferOrder transfer) {
        log.info("Create new order");

        return transferOrderService.getById(transferOrderService.create(transfer).getId());
    }

    @PreAuthorize("inGroupOrAdmin(@transferOrderService.getById(#id), 'delete')")
    @RequestMapping(value = "/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        log.info("Delete order by id" + id);
        transferOrderService.delete(id);
    }
}
