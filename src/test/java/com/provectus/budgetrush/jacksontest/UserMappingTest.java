package com.provectus.budgetrush.jacksontest;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.datatest.InMemoryConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryConfig.class, ObjectMapper.class, User.class })
@WebAppConfiguration
public class UserMappingTest {

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
        File file = new File("user.json");
        User user = new User();
        User user1;

        user.setName("test_name");
        user.setPassword("test_pass");

        log.info("Writing JSON to file");
        mapper.writeValue(file, user);
        scanner = new Scanner(file);
        log.info(scanner.nextLine());
        scanner.close();

        log.info("Cresting POJO from JSON");
        user1 = mapper.readValue(file, User.class);

        log.info(user1.toString());
        file.delete();

        assertNotNull(file.toString(), user1);
    }

}
