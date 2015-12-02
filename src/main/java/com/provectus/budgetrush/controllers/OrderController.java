package com.provectus.budgetrush.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.provectus.budgetrush.data.Order;
import com.provectus.budgetrush.data.PeriodsEnum;
import com.provectus.budgetrush.dateprocessor.DateProcessor;
import com.provectus.budgetrush.dateprocessor.Period;
import com.provectus.budgetrush.service.OrderService;

@Slf4j
@RequestMapping(value = "/v1/orders", headers = "Accept=application/json")
@PreAuthorize("hasRole('ROLE_USER')")
@RestController
public class OrderController {

	@Autowired
	private OrderService service;

	@Autowired
	private DateProcessor dateProcessor;

	@PostFilter("isObjectOwnerOrAdmin(filterObject, 'read')")
	@RequestMapping(method = GET)
	@ResponseBody
	public List<Order> listAll(
			@RequestParam(required = false) PeriodsEnum period,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate) {

		if (period == null) {
			log.info("Get all orders");
			return service.getAll();
		}

		Period createdPeriod = dateProcessor.createPeriod(period, startDate,
				endDate);
		log.info("Get orders by period.");

		return service.getOrdersByPeriod(createdPeriod.getStartDate(),
				createdPeriod.getEndDate());

	}

	@PostAuthorize("isObjectOwnerOrAdmin(returnObject, 'read')")
	@RequestMapping(value = "/{id}", method = GET)
	@ResponseBody
	public Order getById(@PathVariable Integer id) {
		log.info("Get order by id" + id);
		return service.getById(id);
	}

	@PreAuthorize("isObjectOwnerOrAdmin(#order, 'write')")
	@RequestMapping(method = POST)
	@ResponseBody
	public Order create(@RequestBody Order order) {
		log.info("Create/update new order");

		return service.create(order);
	}

	@PreAuthorize("isObjectOwnerOrAdmin(#order, 'write')")
	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseBody
	public Order update(@RequestBody Order order, @PathVariable Integer id) {
		log.info("Create/update order id " + id);
		return service.update(order, id);
	}

	@PreAuthorize("isObjectOwnerOrAdmin(@orderService.getById(#id), 'delete')")
	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		log.info("Delete order by id" + id);
		service.delete(id);
	}
}
