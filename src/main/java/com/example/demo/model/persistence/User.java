package com.example.demo.model.persistence;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "user")
public class User {

	private static final Logger logger = LoggerFactory.getLogger(User.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private long id;

	@Column(nullable = false, unique = true)
	@JsonProperty
	private String username;

	/**
	 * From video ND035 C04 L01 A06.2
	 * P4-L1-12
	 */
	@Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String salt;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	@JsonIgnore
	private Cart cart;

	public Cart getCart() {
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.debug("[{}] Value: {}", thisMethode, cart);
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.debug("[{}] Value: {}", thisMethode, this.cart);
	}

	public long getId() {
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.debug("[{}] Value: {}", thisMethode, id);
		return id;
	}

	public void setId(long id) {
		this.id = id;
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.debug("[{}] Value: {}", thisMethode, this.id);
	}

	public String getUsername() {
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.debug("[{}] Value: {}", thisMethode, username);
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.debug("[{}] Value: {}", thisMethode, this.username);
	}

	public String getPassword() {
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.debug("[{}] Value (hash): {}", thisMethode, password);
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.debug("[{}] Value (hash): {}", thisMethode, this.password);
	}

	public String getSalt() {
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.debug("[{}] Value: {}", thisMethode, salt);
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.debug("[{}] Value: {}", thisMethode, this.salt);
	}
}
