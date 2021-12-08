package com.example.demo.requests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import com.example.demo.model.requests.ModifyCartRequest;

public class ModifyCartRequestTest {
    // https://stackoverflow.com/questions/473401/get-name-of-currently-executing-test-in-junit-4
    @Rule
    public TestName testName = new TestName();

    private ModifyCartRequest modifyCartRequestTest = new ModifyCartRequest();

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
    public void setUsername_getUsername(){
        modifyCartRequestTest.setUsername("the_User");
        System.out.println(modifyCartRequestTest.getUsername());
        assertEquals("the_User",modifyCartRequestTest.getUsername());
    }

    @Test
    public void setItemId_getItemId(){
        Long idTest = Long.valueOf(44);
        modifyCartRequestTest.setItemId(idTest);
        System.out.println(modifyCartRequestTest.getItemId());
        assertEquals(Long.valueOf(idTest) , Long.valueOf(modifyCartRequestTest.getItemId()));
    }

    @Test
    public void setQuantity_getQuantity(){
        int quantityTest = 13;
        modifyCartRequestTest.setQuantity(quantityTest);
        System.out.println(modifyCartRequestTest.getQuantity());
        assertEquals(quantityTest , modifyCartRequestTest.getQuantity());
    }
}
