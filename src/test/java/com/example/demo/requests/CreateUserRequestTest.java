package com.example.demo.requests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import com.example.demo.model.persistence.User;
import com.example.demo.model.requests.CreateUserRequest;

public class CreateUserRequestTest {
    // https://stackoverflow.com/questions/473401/get-name-of-currently-executing-test-in-junit-4
    @Rule
    public TestName testName = new TestName();

    private CreateUserRequest createUserRequestTest = new CreateUserRequest();

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
        createUserRequestTest.setUsername("the_User");
        System.out.println(createUserRequestTest.getUsername());
        assertEquals("the_User",createUserRequestTest.getUsername());
    }

    @Test
    public void setPassword_getPassword(){
        createUserRequestTest.setPassword("userPassword");
        System.out.println(createUserRequestTest.getPassword());
        assertEquals("userPassword",createUserRequestTest.getPassword());
    }

    @Test
    public void setConfirmPassword_getConfirmPassword(){
        createUserRequestTest.setConfirmPassword("userPassword");
        System.out.println(createUserRequestTest.getConfirmPassword());
        assertEquals("userPassword",createUserRequestTest.getConfirmPassword());
    }
}
