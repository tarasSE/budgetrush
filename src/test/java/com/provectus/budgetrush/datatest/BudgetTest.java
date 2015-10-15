package com.provectus.budgetrush.datatest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

import com.provectus.budgetrush.data.Budget;
import com.provectus.budgetrush.service.AccountService;
import com.provectus.budgetrush.service.BudgetService;
import com.provectus.budgetrush.service.CategoryService;
import com.provectus.budgetrush.service.ContractorService;
import com.provectus.budgetrush.service.GroupService;
import com.provectus.budgetrush.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { InMemoryConfig.class, BudgetService.class, OrderService.class,
        AccountService.class, CategoryService.class, GroupService.class, ContractorService.class })
@WebAppConfiguration
public class BudgetTest {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private BudgetService service;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GroupService groupService;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }

    private Budget saveTestBudget() {
        log.info("Start save test category");
        Budget budget = new Budget();

        budget.setId(1);
        budget.setName("Budget");
        budget.setCategory(categoryService.getById(1));
        budget.setStartDate(new Date());
        budget.setEndDate(new Date());
        budget.setGroup(groupService.getById(1));
        budget.setAmount(BigDecimal.ONE);

        return service.create(budget);
    }

    @Test
    @Transactional
    public void saveBudgetTest() throws Exception {

        Budget budget = saveTestBudget();
        Budget budget1 = service.getById(budget.getId());
        assertNotNull(budget1.getId());

    }

    @Test
    @Transactional
    public void getAllBudgetsTest() throws Exception {
        log.info("Start get all test");
        int size = service.getAll().size();
        saveTestBudget();
        List<Budget> budgets = service.getAll();
        for (Budget budget : budgets) {
            log.info("Budget " + budget.getName() + " id " + budget.getId());
        }

        assertNotEquals(size, budgets.size());
    }

    @Test
    @Transactional
    public void getByIdTest() throws Exception {
        Budget budget = saveTestBudget();
        Budget budget1 = service.getById(budget.getId());

        assertEquals(budget.getId(), budget1.getId());
        log.info("id1 " + budget.getId() + " id2 " + budget1.getId());
    }

    @Test
    @Transactional
    public void deleteBudgetTest() throws Exception {
        Budget budget = saveTestBudget();
        service.delete(budget.getId());

        log.info("id  " + budget.getId());
    }
}
