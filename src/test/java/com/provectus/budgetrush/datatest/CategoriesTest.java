package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.service.CategoryService;
import com.provectus.budgetrush.service.CategoryServiceBean;
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
@ContextConfiguration(classes = { HibernateConfig.class, CategoryServiceBean.class })
@WebAppConfiguration
public class CategoriesTest {

        private final Random random = new Random();
        @Resource
        private EntityManagerFactory emf;
        protected EntityManager em;

        @Autowired
        private CategoryService service;

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
            return service.addCategory(category);
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
        public void getByNameTest() throws Exception {
            Category category = saveTestCategory();
            Category category1= service.getByName(category.getName());

            assertEquals(category.getName(), category1.getName());
            assertEquals(category.getId(), category1.getId());
            log.info("id1 " + category.getId() + " id2 " + category1.getId());
        }

        @Test
        @Transactional
        public void getByIdTest() throws Exception {
            Category category = saveTestCategory();
            Category category1 = service.getByID(category.getId());

            assertEquals(category.getId(), category1.getId());
            log.info("id1 " + category.getId() + " id2 " + category1.getId());
        }
    }


