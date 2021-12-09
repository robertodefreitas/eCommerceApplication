package com.example.demo.requests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.example.demo.TestUtils;
import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;

//@RunWith(SpringRunner.class) //jUnit4 mechanism, JUnit5 support comes out of the box
//@WebMvcTest(UserController.class)
//@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class UserControllerTest {

    private UserController userController;
    private BCrypt bCrypt;

    // mock object
    private UserRepository userRepo = mock(UserRepository.class);
    private CartRepository cartRepo = mock(CartRepository.class);
    private BCryptPasswordEncoder encoder1 = mock(BCryptPasswordEncoder.class);
    private BCrypt encoder2 = mock(BCrypt.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @Before
    public void setUp(){
        userController = new UserController();
        TestUtils.injectObjects(userController, "userRepository", userRepo);
        TestUtils.injectObjects(userController, "cartRepository", cartRepo);
        TestUtils.injectObjects(userController, "bCryptPasswordEncoder", encoder1);
        TestUtils.injectObjects(userController, "bCrypt", encoder2);
    }


    @Test
    public void create_user_happy_path() throws Exception {
        //when(encoder1.encode("testPassword")).thenReturn("thisIsHashed");

        /**
         * Also, this error might show up because:
         * 1. you stub either of: final/private/equals()/hashCode() methods.
         *    Those methods *cannot* be stubbed/verified.
         *    Mocking methods declared on non-public parent classes is not supported.
         * 2. inside when() you don't call method on mock but on some other object.
         */
        //when(encoder2.hashpw("testPW", encoder2.gensalt())).thenReturn("thisIsHashed");

        CreateUserRequest userRequest = new CreateUserRequest();

        userRequest.setUsername("userTest");
        userRequest.setPassword("passwordTest");
        userRequest.setConfirmPassword("passwordTest");

        final ResponseEntity<User> response = userController.createUser(userRequest);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        User user = response.getBody();
        String pwSalt = bCrypt.hashpw("passwordTest",user.getSalt());
        assertNotNull(user);
        assertEquals(0, user.getId());
        assertEquals("userTest", user.getUsername());
        assertEquals(pwSalt, user.getPassword());
        //assertEquals("thisIsHashed", u.getPassword());
    }


    @Test
    public void check_username() throws Exception {
        CreateUserRequest userRequest = new CreateUserRequest();

        userRequest.setUsername("userTest");
        userRequest.setPassword("passwordTest");
        userRequest.setConfirmPassword("passwordTest");

        final ResponseEntity<User> response = userController.createUser(userRequest);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());


        /**
         * https://docs.spring.io/spring-security/site/docs/4.0.x/reference/htmlsingle/#test-mockmvc-securitycontextholder-rpp
         * https://docs.spring.io/spring-security/site/docs/4.0.x/reference/htmlsingle/#test-mockmvc
         * ERROR: java.lang.IllegalArgumentException: WebApplicationContext is required
         */
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
//        this.mockMvc.perform(post("/login").accept(MediaType.ALL)).andExpect(status().isOk());

//        // http://techdive.in/solutions/how-mock-securitycontextholder-perfrom-junit-tests-spring-controller
//        User user = new User();
//        Authentication auth = new UsernamePasswordAuthenticationToken(user,null);
//        SecurityContextHolder.getContext().setAuthentication(auth);

        ResponseEntity<User> response2 = userController.findByUserName(userRequest.getUsername());
        assertNotNull(response2);
        assertEquals(200, response2.getStatusCodeValue());
    }

}
