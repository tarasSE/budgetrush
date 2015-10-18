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

import com.provectus.budgetrush.data.Group;
import com.provectus.budgetrush.service.GroupService;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryConfig.class, GroupService.class })
@WebAppConfiguration
public class GroupTest {

    private final Random random = new Random();
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private GroupService groupService;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }

    private Group saveTestGroup() {
        log.info("Start save test Account");
        Group group = new Group();

        group.setName(Integer.toString(random.nextInt()));

        return groupService.create(group);
    }

    @Test
    @Transactional
    public void saveAccountTest() throws Exception {
        Group group = saveTestGroup();
        assertNotNull(group.getId());
    }

    @Test
    @Transactional
    public void getAllCategoriesTest() throws Exception {
        log.info("Start get all test");
        int size = groupService.getAll().size();
        saveTestGroup();
        List<Group> groups = groupService.getAll();
        for (Group group : groups) {
            log.info("Account " + group.getName() + " id " + group.getId());
        }

        assertNotEquals(size, groups.size());
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        Group group = saveTestGroup();
        Group group1 = groupService.getById(group.getId());

        assertEquals(group.getId(), group1.getId());
        log.info("id1 " + group.getId() + " id2 " + group1.getId());
    }

    @Test
    @Transactional
    public void deleteAccountTest() throws Exception {
        Group group = saveTestGroup();
        groupService.delete(group.getId());

        log.info("id  " + group.getId());
    }
}
