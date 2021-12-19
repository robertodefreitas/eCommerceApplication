package com.example.demo.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.TestUtils;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;

public class OrderController {

    private OrderController orderController;

    // mock object
    private UserRepository userRepo = mock(UserRepository.class);
    private CartRepository cartRepo = mock(CartRepository.class);
    private OrderRepository orderRepo = mock(OrderRepository.class);

    @Before
    public void setUp() {
        orderController = new OrderController();
        TestUtils.injectObjects(orderController, "userRepository", userRepo);
        TestUtils.injectObjects(orderController, "cartRepository", cartRepo);
        TestUtils.injectObjects(orderController, "orderRepository", orderRepo);
    }

    private User createMockUser() {
        User mockUser = mock(User.class);
        //1L == (long)1
        //mockUser.setId(1L);
        mockUser.setId((long) 1);
        mockUser.setUsername("userTest");
        return mockUser;
    }

//    @Test
//    public void submitTest() throws Exception {
//        User mockUser = createMockUser();
//        when(userRepo.findByUsername(mockUser.getUsername())).thenReturn(mockUser);
//
//        // Check the methode in UserController
//        final ResponseEntity<UserOrder> responseEntity = orderController.submit();
//        User user = responseEntity.getBody();
//
//        assertNotNull(responseEntity);
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertNotNull(user);
//        assertEquals(mockUser.getId(), user.getId());
//        assertEquals(mockUser.getUsername(), user.getUsername());
//    }

}