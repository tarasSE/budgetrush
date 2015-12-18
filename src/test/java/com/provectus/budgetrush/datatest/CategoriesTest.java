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

import com.provectus.budgetrush.data.category.Category;
import com.provectus.budgetrush.service.CategoryService;
import com.provectus.budgetrush.service.GroupService;
import com.provectus.budgetrush.service.UserService;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryConfig.class, CategoryService.class, UserService.class, GroupService.class })
@WebAppConfiguration
public class CategoriesTest {

    private final Random random = new Random();
    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private CategoryService service;
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }

    private Category saveTestCategory() {
        log.info("Start save test category");
        Category category = new Category();

        category.setName(Integer.toString(random.nextInt()));
        category.setParent(null);
        category.setUser(userService.getById(1));
        return service.create(category);
    }

    @Test
    @Transactional
    public void saveCategoryTest() throws Exception {

        Category category = saveTestCategory();
        assertNotNull(category.getId());

    }

    @Test
    @Transactional
    public void getAllCategoriesTest() throws Exception {
        log.info("Start get all test");
        int size = service.getAll().size();
        saveTestCategory();
        List<Category> categories = service.getAll();
        for (Category category : categories) {
            log.info("Category " + category.getName() + " id " + category.getId());
        }

        assertNotEquals(size, categories.size());
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        Category category = saveTestCategory();
        Category category1 = service.getById(category.getId());

        assertEquals(category.getId(), category1.getId());
        log.info("id1 " + category.getId() + " id2 " + category1.getId());
    }

    @Test
    @Transactional
    public void changeParentTest() throws Exception {
        Category category = saveTestCategory();
        Category category1 = saveTestCategory();
        category.setParent(category1);
        service.update(category, category.getId());

        assertNotNull(category.getParent());
        log.info("id1 " + category.getId() + " id2 " + category1.getId());
    }

    @Test
    @Transactional
    public void deleteCategoryTest() throws Exception {
        Category category = saveTestCategory();
        service.delete(category.getId());

        log.info("id  " + category.getId());
    }
}
