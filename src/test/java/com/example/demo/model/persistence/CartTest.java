package com.example.demo.model.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class CartTest {

    // https://stackoverflow.com/questions/473401/get-name-of-currently-executing-test-in-junit-4
    @Rule
    public TestName testName = new TestName();

    private Cart cartTest = new Cart();

    // This method must be public and static
    @BeforeClass
    public static void initClass() {
        System.out.println("");
        System.out.println("###########################");
        System.out.println("### init Class executed ###");
    }
    @AfterClass
    public static void teardownclass() {
        System.out.println("### teardown Class executed ###");
        System.out.println("###############################");
        System.out.println("");
    }

    @Before
    public void init() {
        System.out.println("init executed: " + testName.getMethodName()); }
    @After
    public void teardown() {
        System.out.println("teardown executed: " + testName.getMethodName());
        System.out.println("---");
    }


    @Test
    public void setTotal_and_getTotal(){
        BigDecimal totalTest = BigDecimal.valueOf(777.77);
        cartTest.setTotal(totalTest);
        System.out.println(cartTest.getTotal());
        assertEquals(totalTest,cartTest.getTotal());
    }

    @Test
    public void setUser_and_getUser(){
        User userTest = new User();
        cartTest.setUser(userTest);
        System.out.println(cartTest.getUser());
        assertNotNull(cartTest.getUser());
    }

    @Test
    public void setId_and_getId(){
        Long idTest = Long.valueOf(33);
        cartTest.setId(idTest);
        System.out.println(cartTest.getId());
        assertEquals(idTest,cartTest.getId());
    }

    @Test
    public void setItems_and_getItems(){
        // https://mkyong.com/unittest/junit-how-to-test-a-list/
        List<Item> itemListTest = Arrays.asList(new Item(), new Item());
        cartTest.setItems(itemListTest);
        System.out.println(cartTest.getItems());
        assertNotNull(cartTest.getItems());
    }

    @Test
    public void addItems_and_removeItems(){
        Item itemTest1 = new Item();
        BigDecimal value1Test = BigDecimal.valueOf(22);
        BigDecimal value2Test = BigDecimal.valueOf(10);
        BigDecimal value3Test = BigDecimal.valueOf(12);

        // set the item price 22
        itemTest1.setPrice(value1Test);
        cartTest.addItem(itemTest1);
        System.out.println(cartTest.getTotal());
        assertEquals(value1Test,cartTest.getTotal());

        // set a new item price 10, subtract 10 from 22
        itemTest1.setPrice(value2Test);
        cartTest.removeItem(itemTest1);
        System.out.println(cartTest.getTotal());
        assertEquals(value3Test,cartTest.getTotal());
    }


}
