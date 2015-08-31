package com.provectus.budgetrush;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.repository.CategoryRepository;
import com.provectus.budgetrush.service.CategoryService;
import com.provectus.budgetrush.utils.HibernateConfig;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
@WebAppConfiguration
public class CategoryTest {

    private final String CATEGORY_NAME = "goods";

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private CategoryRepository repo;
    @Resource
    private CategoryService service;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testRepo() {
        repo.findByName(CATEGORY_NAME);
    }

    @Test
    @Transactional
    public void testSaveCategory() throws Exception {
        Category category = new Category();
        category.setName(CATEGORY_NAME);
        service.addCategory(category);

        assertNotNull(category.getId());
    }

    @Test
    @Transactional
    public void testgetByNameCategory() throws Exception {
        Category category = new Category();
        category.setName(CATEGORY_NAME);
        service.addCategory(category);

        Category getcategory = service.getByName(CATEGORY_NAME);

        assertEquals(getcategory.getName(), CATEGORY_NAME);
    }

    @Test
    @Transactional
    public void testgetByIDCategory() throws Exception {
        Category category = new Category();
        category.setName(CATEGORY_NAME);
        service.addCategory(category);

        Category getcategory = service.getByID(0);

        assertEquals(getcategory.getName(), CATEGORY_NAME);
    }

    @Test
    @Transactional
    public void testdeleteCategory() throws Exception {
        int size = service.getAll().size();
        service.delete(0);

        assertEquals(size - 1, service.getAll().size());
    }
}
