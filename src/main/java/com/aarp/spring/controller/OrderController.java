package com.aarp.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.aarp.spring.model.PersonOrder;
import com.aarp.spring.repo.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@GetMapping("/personOrders")
	public List<PersonOrder> getAllPersonOrders() {
		System.out.println("Get all personOrders...");

		List<PersonOrder> list = new ArrayList<>();
		Iterable<PersonOrder> customers = orderRepository.findAll();

		customers.forEach(list::add);
		return list;
	}

	@PostMapping("/personOrders/create")
	public PersonOrder createPersonOrder(@Valid @RequestBody PersonOrder personOrder) {
		System.out.println("Create PersonOrder: " + personOrder.getEmail() + "...");

		return orderRepository.save(personOrder);
	}

	@GetMapping("/personOrders/{id}")
	public ResponseEntity<PersonOrder> getPersonOrder(@PathVariable("id") Long id) {
		System.out.println("Get PersonOrder by id...");

		Optional<PersonOrder> personOrderData = orderRepository.findById(id);
		if (personOrderData.isPresent()) {
			return new ResponseEntity<>(personOrderData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/personOrders/{id}")
	public ResponseEntity<PersonOrder> updatePersonOrder(@PathVariable("id") Long id, @RequestBody PersonOrder personOrder) {
		System.out.println("Update PersonOrder with ID = " + id + "...");

		Optional<PersonOrder> personOrderData = orderRepository.findById(id);
		if (personOrderData.isPresent()) {
			PersonOrder savedPersonOrder = personOrderData.get();
			savedPersonOrder.setRecordId(personOrder.getRecordId());
			savedPersonOrder.setEmail(personOrder.getEmail());
			savedPersonOrder.setAddress1(personOrder.getAddress1());
			savedPersonOrder.setAddress2(personOrder.getAddress2());
			savedPersonOrder.setZipcode(personOrder.getZipcode());

			PersonOrder updatedPersonOrder = orderRepository.save(savedPersonOrder);
			return new ResponseEntity<>(updatedPersonOrder, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/personOrders/{id}")
	public ResponseEntity<String> deletePersonOrder(@PathVariable("id") Long id) {
		System.out.println("Delete PersonOrder with ID = " + id + "...");

		try {
			orderRepository.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<>("Order has been deleted!", HttpStatus.OK);
	}
}
