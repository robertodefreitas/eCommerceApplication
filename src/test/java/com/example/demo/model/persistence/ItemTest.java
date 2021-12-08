package com.example.demo.model.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class ItemTest {

    // https://stackoverflow.com/questions/473401/get-name-of-currently-executing-test-in-junit-4
    @Rule
    public TestName testName = new TestName();

    private Item itemTest = new Item();

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
        Long idTest = Long.valueOf(22);
        itemTest.setId(idTest);
        System.out.println(itemTest.getId());
        assertEquals(idTest,itemTest.getId());
    }

    @Test
    public void setName_and_getName(){
        itemTest.setName("the_User");
        System.out.println(itemTest.getName());
        assertEquals("the_User",itemTest.getName());
    }

    @Test
    public void setPrice_and_getPrice(){
        BigDecimal priceTest = BigDecimal.valueOf(888.88);
        itemTest.setPrice(priceTest);
        System.out.println(itemTest.getPrice());
        assertEquals(priceTest,itemTest.getPrice());
    }

    @Test
    public void setDescription_and_getDescription(){
        itemTest.setDescription("a_Description");
        System.out.println(itemTest.getDescription());
        assertEquals("a_Description",itemTest.getDescription());
    }

    @Test
    public void hashCodeTest(){
        itemTest.setId(Long.valueOf(11));
        int hashTest = itemTest.hashCode();
        System.out.println(hashTest);
        assertNotNull(hashTest);
    }
}
