package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.Contractor;
import com.provectus.budgetrush.service.ContractorServiceBean;
import com.provectus.budgetrush.utils.HibernateConfig;
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
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;


@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfig.class, ContractorServiceBean.class })
@WebAppConfiguration
public class ContractorsTest {

        private final Random random = new Random();
        @Resource
        private EntityManagerFactory emf;
        protected EntityManager em;

        @Autowired
        private ContractorService service;

        @Before
        public void setUp() throws Exception {
            log.info("Init entity manager");
            em = emf.createEntityManager();
        }

        private Contractor saveTestContractor() {
            log.info("Start save test contractor");
            Contractor contractor = new Contractor();

            contractor.setName(Integer.toString(random.nextInt()));
            return service.addContractor(contractor);
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
        public void getByNameTest() throws Exception {
            Contractor contractor = saveTestContractor();
            Contractor contractor1= service.getByName(contractor.getName());

            assertEquals(contractor.getName(), contractor1.getName());
            assertEquals(contractor.getId(), contractor1.getId());
            log.info("id1 " + contractor.getId() + " id2 " + contractor1.getId());
        }

        @Test
        @Transactional
        public void getByIdTest() throws Exception {
            Contractor contractor = saveTestContractor();
            Contractor contractor1 = service.getByID(contractor.getId());

            assertEquals(contractor.getId(), contractor1.getId());
            log.info("id1 " + contractor.getId() + " id2 " + contractor1.getId());
        }

        @Test
        @Transactional
        public void editContractorTest() throws Exception {
            Contractor contractor = saveTestContractor();
            Contractor contractor1 = service.getByID(contractor.getId());

            contractor1.setName("test_name");
            service.editContractor(contractor1);
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


