package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SareetaApplicationTests {

	SareetaApplication sareetaApplication = new
			SareetaApplication();

	@Test
	public void contextLoads() {
	}

	@Test
	public void bCryptPasswordEncoderTest(){
		BCryptPasswordEncoder resultTest = sareetaApplication.bCryptPasswordEncoder();
		System.out.println(resultTest);
		assertNotNull(resultTest);
	}

	@Test
	public void bCryptTest(){
		BCrypt resultTest = sareetaApplication.bCrypt();
		System.out.println(resultTest);
		assertNotNull(resultTest);
	}
}
