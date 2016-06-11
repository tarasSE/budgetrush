package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.config.InMemoryConfig;
import com.provectus.budgetrush.data.BaseEntity;
import com.provectus.budgetrush.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InMemoryConfig.class})

public abstract class TestGenericService<E extends BaseEntity, S extends GenericService<E, ?>> {
    protected final static Random random = new Random();

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
        before();
    }

    @Test
    @Transactional
    public void create() {
        BaseEntity entity = getEntityAndSave();
        assertNotNull(entity.getId());
    }

    @Test
    @Transactional
    public void update(){}

    @Test
    @Transactional
    public void getAll() {
        int size = getService().getAll().size();
        create();
        List<E> entities = getService().getAll();

        assertNotEquals(size, entities.size());
    }

    @Test
    @Transactional
    public void getById() {
        E entity = getEntityAndSave();
        E anotherEntity = getService().getById(entity.getId());

        assertEquals(entity.getId(), entity.getId());
    }

    @Test
    @Transactional
    public void delete() {
        E entity = getService().getById(1);
        getService().delete(entity.getId());

        System.out.println(getService().getById(entity.getId()));
    }

    protected void before() {
    }

    protected E getEntityAndSave(){
        return getService().create(getEntity());
    }

    protected abstract E getEntity();

    protected abstract S getService();
}
