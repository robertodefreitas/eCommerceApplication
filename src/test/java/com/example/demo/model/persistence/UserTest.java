package com.example.demo.model.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class UserTest {

    // https://stackoverflow.com/questions/473401/get-name-of-currently-executing-test-in-junit-4
    @Rule
    public TestName testName = new TestName();

    private User userTest = new User();

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

//    @Test
//    public void getCart(){
//        System.out.println(userTest.getCart());
//        assertNull(userTest.getCart());
//    }

    @Test
    public void setCart_and_getCart(){
        Cart cartTest = new Cart();
        userTest.setCart(cartTest);
        System.out.println(userTest.getCart());
        assertNotNull(userTest.getCart());
    }

    @Test
    public void setId_and_getId(){
        userTest.setId(11);
        System.out.println(userTest.getId());
        assertEquals(11,userTest.getId());
    }

    @Test
    public void setUsername_and_getUsername(){
        userTest.setUsername("the_User");
        System.out.println(userTest.getUsername());
        assertEquals("the_User",userTest.getUsername());
    }

    @Test
    public void setPassword_and_getPassword(){
        userTest.setPassword("userPassword");
        System.out.println(userTest.getPassword());
        assertEquals("userPassword",userTest.getPassword());
    }

    @Test
    public void setSalt_and_getSalt(){
        userTest.setSalt("testSalt");
        System.out.println(userTest.getSalt());
        assertEquals("testSalt",userTest.getSalt());
    }
}
