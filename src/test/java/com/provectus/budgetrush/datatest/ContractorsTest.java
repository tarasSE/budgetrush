package com.provectus.budgetrush.datatest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

import com.provectus.budgetrush.data.Contractor;
import com.provectus.budgetrush.service.ContractorService;
import com.provectus.budgetrush.service.GroupService;
import com.provectus.budgetrush.service.UserService;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
                      classes = { InMemoryConfig.class, ContractorService.class, UserService.class, GroupService.class })
@WebAppConfiguration
public class ContractorsTest {

    private final Random random = new Random();
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private ContractorService service;
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }

    private Contractor saveTestContractor() {
        log.info("Start save test contractor");
        Contractor contractor = new Contractor();

        contractor.setName(Integer.toString(random.nextInt()));
        contractor.setUser(userService.getById(1));
        return service.create(contractor);
    }

    @Test
    @Transactional
    public void saveContracorTest() throws Exception {

        Contractor contractor = saveTestContractor();
        assertNotNull(contractor.getId());

    }

    @Test
    @Transactional
    public void getAllContractorsTest() throws Exception {
        log.info("Start get all test");
        int size = service.getAll().size();
        saveTestContractor();
        List<Contractor> contractors = service.getAll();
        for (Contractor contractor : contractors) {
            log.info("Contractor " + contractor.getName() + " id " + contractor.getId());
        }

        assertNotEquals(size, contractors.size());
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        Contractor contractor = saveTestContractor();
        Contractor contractor1 = service.getById(contractor.getId());

        assertEquals(contractor.getId(), contractor1.getId());
        log.info("id1 " + contractor.getId() + " id2 " + contractor1.getId());
    }

    @Test
    @Transactional
    public void deleteContractorTest() throws Exception {
        Contractor contractor = saveTestContractor();
        service.delete(contractor.getId());

        log.info("id  " + contractor.getId());
    }
}
