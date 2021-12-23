package com.example.demo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;

@RestController
@RequestMapping("/api/item")
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Item>> getItems() {
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[{}] Mapping: /api/item/getAll (findAll)", thisMethode);

		return ResponseEntity.ok(itemRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable Long id) {
		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[{}] Mapping: /api/item/{}", thisMethode, id);

		return ResponseEntity.of(itemRepository.findById(id));
	}
	
	@GetMapping("/name/{itemName}")
	public ResponseEntity<List<Item>> getItemsByName(@PathVariable String itemName) {
		List<Item> items = itemRepository.findByName(itemName);

		String thisMethode = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[{}] Mapping: /api/item/name/{}", thisMethode, itemName);

		return items == null || items.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(items);
			
	}
	
}
