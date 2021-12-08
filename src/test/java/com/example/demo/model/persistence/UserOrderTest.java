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

public class UserOrderTest {

    // https://stackoverflow.com/questions/473401/get-name-of-currently-executing-test-in-junit-4
    @Rule
    public TestName testName = new TestName();

    private UserOrder userOrderTest = new UserOrder();

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
    public void setId_and_getId(){
        Long idTest = Long.valueOf(11);
        userOrderTest.setId(idTest);
        System.out.println(userOrderTest.getId());
        assertEquals(idTest,userOrderTest.getId());
    }

    @Test
    public void setItems_and_getItems(){
        // https://mkyong.com/unittest/junit-how-to-test-a-list/
        List<Item> itemListTest = Arrays.asList(new Item(), new Item());
        userOrderTest.setItems(itemListTest);
        System.out.println(userOrderTest.getItems());
        assertNotNull(userOrderTest.getItems());
    }

    @Test
    public void setUser_and_getUser(){
        User testUser = new User();
        userOrderTest.setUser(testUser);
        System.out.println(userOrderTest.getUser());
        assertNotNull(userOrderTest.getUser());
    }

    @Test
    public void setTotal_and_getTotal(){
        BigDecimal totalTest = BigDecimal.valueOf(999.99);
        userOrderTest.setTotal(totalTest);
        System.out.println(userOrderTest.getTotal());
        assertEquals(totalTest,userOrderTest.getTotal());
    }

    @Test
    public void createFromCart(){
        // https://mkyong.com/unittest/junit-how-to-test-a-list/
        List<Item> itemListTest = Arrays.asList(new Item(), new Item());
        Cart cartTest = new Cart();
        cartTest.setItems(itemListTest);
        UserOrder orderTest = userOrderTest.createFromCart(cartTest);
        System.out.println(orderTest);
        assertNotNull(orderTest);
    }
}
