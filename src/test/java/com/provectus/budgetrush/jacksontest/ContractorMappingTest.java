package com.provectus.budgetrush.jacksontest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provectus.budgetrush.data.Contractor;
import com.provectus.budgetrush.utils.HibernateConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfig.class, ObjectMapper.class, Contractor.class})
@WebAppConfiguration
public class ContractorMappingTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        log.info("Init object mapper");
        mapper = new ObjectMapper();
    }

    @Test
    @Transactional
    public void jsonMappingTest() throws Exception {
        Scanner scanner;
        File file = new File("contractor.json");
        Contractor contractor = new Contractor();
        Contractor contractor1;

        contractor.setName("test_name");
        contractor.setDescription("test_description");

        log.info("Writing JSON to file");
        mapper.writeValue(file, contractor);
        scanner = new Scanner(file);
        log.info(scanner.nextLine());
        scanner.close();

        log.info("Cresting POJO from JSON");
        contractor1 = mapper.readValue(file, Contractor.class);
        log.info(contractor1.toString());
        file.delete();

        assertNotNull(file.toString(), contractor1);
    }



}
