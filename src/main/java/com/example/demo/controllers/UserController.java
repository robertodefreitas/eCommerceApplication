package com.example.demo.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;

	/**
	 * From video ND035 C04 L01 A06.2
	 * P4-L1-12
	 */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/id/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		return ResponseEntity.of(userRepository.findById(id));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUserName(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
		User user = new User();
		user.setUsername(createUserRequest.getUsername());
		Cart cart = new Cart();
		cartRepository.save(cart);
		user.setCart(cart);

		/**
		 * From video ND035 C04 L01 A06.2
		 * P4-L1-12
		 */
		if(createUserRequest.getPassword().length() < 7 ||
			!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())){
			//log.error("Error with user password. Cannot create user {}", createUserRequest.getUsername());
			return ResponseEntity.badRequest().build();
		}


		// Method to generate the hash.
		//It takes a password and the Salt as input arguments
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			//md.update(user.createSalt());
			user.setSalt();
			md.update(Base64.getDecoder().decode(user.getSalt()));

			byte[] bytes = md.digest(createUserRequest.getPassword().getBytes());
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		user.setPassword(generatedPassword);

		//user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));

		/**
		 * Created to see the values
		 * robertodefreitas
		 */
		System.out.println("### user.getUsername: " + user.getUsername());
		System.out.println("### createUserRequest.getPassword: " + createUserRequest.getPassword());
		System.out.println("### user.getPassword: " + user.getPassword());
		System.out.println("### user.getSalt: " + user.getSalt());

		userRepository.save(user);
		return ResponseEntity.ok(user);
	}
	
}
