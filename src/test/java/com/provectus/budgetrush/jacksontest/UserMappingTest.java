package com.provectus.budgetrush.jacksontest;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Scanner;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provectus.budgetrush.data.user.User;
import com.provectus.budgetrush.datatest.InMemoryConfig;
import com.provectus.budgetrush.service.GroupService;
import com.provectus.budgetrush.service.UserService;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ObjectMapper.class, InMemoryConfig.class, UserService.class, GroupService.class })
@WebAppConfiguration
public class UserMappingTest {

    private ObjectMapper mapper;
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private UserService userService;

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
        File file = new File("user.json");

        log.info("Writing JSON to file");
        mapper.writeValue(file, userService.getById(1));
        scanner = new Scanner(file);
        log.info(scanner.nextLine());
        scanner.close();

        log.info("Cresting POJO from JSON");
        User user = mapper.readValue(file, User.class);

        log.info(user.toString());
        file.delete();

        assertNotNull(file.toString(), user);
    }

}
