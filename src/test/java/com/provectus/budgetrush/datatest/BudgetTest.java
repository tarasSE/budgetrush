package com.provectus.budgetrush.datatest;

import com.provectus.budgetrush.data.Budget;
import com.provectus.budgetrush.data.Category;
import com.provectus.budgetrush.data.Roles;
import com.provectus.budgetrush.data.User;
import com.provectus.budgetrush.service.*;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InMemoryConfig.class, BudgetService.class, OrderService.class,
        AccountService.class, CategoryService.class, UserService.class, ContractorService.class})
@WebAppConfiguration
public class BudgetTest {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Autowired
    private BudgetService service;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ContractorService contractorService;
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        log.info("Init entity manager");
        em = emf.createEntityManager();
    }

    private Budget saveTestBudget() {
        log.info("Start save test category");
        Budget budget = new Budget();
        Category category = new Category();
        User user = new User();

        user.setId(1);
        user.setName("gcbhc");
        user.setPassword("sdsdfsdff");
        user.setRole(Roles.ROLE_USER);
        user = userService.create(user);

        category.setId(1);
        category.setName("cfvbxcf");
        category.setParent(null);
        category.setUser(user);
        category = categoryService.create(category);

        budget.setId(1);
        budget.setName("Budget");
        budget.setCategory(category);
        budget.setStartDate(new Date());
        budget.setEndDate(new Date());
        budget.setUser(user);
        budget.setAmount(BigDecimal.ONE);

        return service.create(budget);
    }

    @Test
    @Transactional
    public void saveBudgetTest() throws Exception {

        Budget budget = saveTestBudget();
       Budget budget1 = service.getById(budget.getId());
        assertNotNull(budget.getId());

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
