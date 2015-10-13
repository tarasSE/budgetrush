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
import com.provectus.budgetrush.data.Category;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ObjectMapper.class, Category.class })
@WebAppConfiguration
public class CategoryMappingTest {

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
        File file = new File("category.json");
        Category category = new Category();
        Category category1;

        category.setName("test_name");
        category.setParent(null);

        log.info("Writing JSON to file");
        mapper.writeValue(file, category);
        scanner = new Scanner(file);
        log.info(scanner.nextLine());
        scanner.close();

        log.info("Cresting POJO from JSON");
        category1 = mapper.readValue(file, Category.class);
        log.info(category1.toString());

        file.delete();

        assertNotNull(file.toString(), category1);
    }

}
