package com.example.demo.controllers;

//import org.apache.log4j.PropertyConfigurator;
//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/api/user")
public class UserController {

	/**
	 * From video ND035 C04 L03 A05
	 * P4-L2-10
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);


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

	@Autowired
	private BCrypt bCrypt;

	// findById is implemented by interface CrudRepository.class
	@GetMapping("/id/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[{}] Mapping: /api/user/id/{}", thisMethode, id);

		return ResponseEntity.of(userRepository.findById(id));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUserName(@PathVariable String username) {
		User user = userRepository.findByUsername(username);

		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[{}] Mapping: /api/user/{}", thisMethode, username);

		return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
		User user = new User();
		user.setUsername(createUserRequest.getUsername());

		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[{}] Mapping: /api/user/create, Username: {}", thisMethode, createUserRequest.getUsername());

		Cart cart = new Cart();
		cartRepository.save(cart);
		user.setCart(cart);


		/**
		 * From video ND035 C04 L01 A06.2
		 * P4-L1-12
		 */
		if(createUserRequest.getPassword().length() < 7 ||
			!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())){
			logger.error("[{}] Error with user password. Cannot create user {}", thisMethode, createUserRequest.getUsername());
			return ResponseEntity.badRequest().build();
		}

		/**
		 * bCrypt hash work with salt and the cipher text (password)
		 * https://stackoverflow.com/questions/6832445/how-can-bcrypt-have-built-in-salts
		 */
		//user.setSalt(bCryptPasswordEncoder.getSalt()); // wrong, because getSalt is private
		//user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));


		/**
		 * to generate a hash with extern salt
		 * it works, but with the mock test dont work
		 */
		user.setSalt(bCrypt.gensalt());
		user.setPassword(bCrypt.hashpw(createUserRequest.getPassword(),user.getSalt()));
		logger.info("[{}] Hash with salt is generated (bCrypt)", thisMethode);
		logger.info("[{}] [INFO] User: {} | Generated salt: {}", thisMethode, user.getUsername(), user.getSalt());

		// Debug works with this row in application.properties
		// logging.level.com.example.demo=DEBUG
		logger.debug("[{}] [DEBUG] User: {} | Generated salt: {}", thisMethode, user.getUsername(), user.getSalt());
		logger.debug("[{}] [DEBUG] User: {} | Generated password: {}", thisMethode, user.getUsername(), user.getPassword());

		// OR Alternativ
		/*
		class cryptPassword {
			void genSalt() {
				//User user = new User();
				//CreateUserRequest createUserRequest = new CreateUserRequest();
				user.setSalt(bCrypt.gensalt());
				user.setPassword(bCrypt.hashpw(createUserRequest.getPassword(), user.getSalt()));
			}
		}
		new cryptPassword().genSalt();
		*/


		/**
		 * Created to see the values (no logs)
		 * robertodefreitas
		 */
//		System.out.println("### user.getUsername: " + user.getUsername());
//		System.out.println("### createUserRequest.getPassword: " + createUserRequest.getPassword());
//		System.out.println("### user.getPassword: " + user.getPassword());
//		System.out.println("### user.getSalt: " + user.getSalt());

		userRepository.save(user);
		return ResponseEntity.ok(user);
	}

}
