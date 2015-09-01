package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.service.UserService;
import com.provectus.budgetrush.service.UserServiceBean;
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
@ContextConfiguration(classes = { HibernateConfig.class, UserServiceBean.class })
@WebAppConfiguration
public class UsersTest {
    private final Random random = new Random();
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private UserService service;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }

    private User saveTestUser() {
        log.info("Start save test user");
        User user = new User();

        user.setName(Integer.toString(random.nextInt()));
        return service.addUser(user);
    }

    @Test
    @Transactional
    public void testSaveUser() throws Exception {

        User user = saveTestUser();
        assertNotNull(user.getId());

    }

    @Test
    @Transactional
    public void testGetAllUsers() throws Exception {
        log.info("Start get all test");
        int size = service.getAll().size();
        saveTestUser();
        List<User> users = service.getAll();
        for (User user : users) {
            log.info("User " + user.getName() + " id " + user.getId());
        }

        assertNotEquals(size, users.size());
    }

    @Test
    @Transactional
    public void testGetByName() throws Exception {
        User user = saveTestUser();
        User user2 = service.getByName(user.getName());

        assertEquals(user.getName(), user2.getName());
        assertEquals(user.getId(), user2.getId());
        log.info("id1 " + user.getId() + " id2 " + user2.getId());
    }

    @Test
    @Transactional
    public void testGetById() throws Exception {
        User user = saveTestUser();
        User user2 = service.getById(user.getId());

        assertEquals(user.getId(), user2.getId());
        log.info("id1 " + user.getId() + " id2 " + user2.getId());
    }

    @Test
    @Transactional
    public void deleteUserTest() throws Exception {
        User user = saveTestUser();
        service.delete(user.getId());

        log.info("id  " + user.getId());
    }
}