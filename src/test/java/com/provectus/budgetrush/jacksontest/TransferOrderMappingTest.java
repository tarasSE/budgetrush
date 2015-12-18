package com.provectus.budgetrush.jacksontest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provectus.budgetrush.data.order.OrderType;
import com.provectus.budgetrush.data.order.TransferOrder;
import com.provectus.budgetrush.datatest.InMemoryConfig;
import com.provectus.budgetrush.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.util.Date;
import java.util.Scanner;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertNotNull;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ObjectMapper.class, InMemoryConfig.class, TransferOrderService.class,
        OrderService.class, AccountService.class, ContractorService.class, CategoryService.class })
@WebAppConfiguration
public class TransferOrderMappingTest {

    private ObjectMapper mapper;
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private TransferOrderService transferOrderService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ContractorService contractorService;

    @Autowired
    private CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        log.info("Init object mapper");
        mapper = new ObjectMapper();
        em = emf.createEntityManager();
    }

    @Test
    @Transactional
    public void jsonMappingTest() throws Exception {
        Scanner scanner;
        File file = new File("order.json");

        TransferOrder transferOrder = new TransferOrder();

        transferOrder.setId(3);
        transferOrder.setAmount(valueOf(999));
        transferOrder.setDate(new Date());
        transferOrder.setAccount(accountService.getById(1));
        transferOrder.setTransferAccount(accountService.getById(2));
        transferOrder.setCategory(categoryService.getById(1));
        transferOrder.setContractor(contractorService.getById(1));
        transferOrder.setType(OrderType.TRANSFER_ORDER);

        log.info("Writing JSON to file");
        mapper.writeValue(file, transferOrderService.create(transferOrder));
        scanner = new Scanner(file);
        log.info(scanner.nextLine());
        scanner.close();

        log.info("Cresting POJO from JSON");
        TransferOrder order = mapper.readValue(file, TransferOrder.class);
        log.info(order.toString());

        file.delete();

        assertNotNull(file.toString(), order);
    }

}
