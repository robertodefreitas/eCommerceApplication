package com.example.demo.requests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.http.ResponseEntity;
import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.UserRepository;

public class UserControllerTest {
    // https://stackoverflow.com/questions/473401/get-name-of-currently-executing-test-in-junit-4
    @Rule
    public TestName testName = new TestName();

    private UserController userControllerTest = new UserController();

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
    public void test(){
        System.out.println("test");
    }
//    @Test
//    public void findByIdTest(){
//        //UserRepository userRepository = new UserRepository();
//        User userTest = new User();
//        userTest.setId(Long.valueOf(77));
//        ResponseEntity<User> resultTest = userControllerTest.findById(Long.valueOf(77));
//        System.out.println(resultTest);
//        assertNotNull(resultTest);
//    }


}
