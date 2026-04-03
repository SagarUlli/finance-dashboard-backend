package com.example.financedashboard.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.financedashboard.dto.TransactionFilterDTO;
import com.example.financedashboard.dto.TransactionRequestDTO;
import com.example.financedashboard.dto.TransactionResponseDTO;
import com.example.financedashboard.entity.Role;
import com.example.financedashboard.service.TransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService service;

	@PostMapping
	public TransactionResponseDTO create(@RequestHeader("X-USER-ID") Long userId, @RequestHeader("X-ROLE") Role role,
			@Valid @RequestBody TransactionRequestDTO request) {

		return service.createTransaction(request, userId, role);
	}

	@GetMapping
	public List<TransactionResponseDTO> getAll(@RequestHeader("X-USER-ID") Long userId,
			@RequestHeader("X-ROLE") Role role) {

		return service.getTransactions(userId, role);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id, @RequestHeader("X-ROLE") Role role) {

		service.deleteTransaction(id, role);
	}

	@GetMapping("/filter")
	public Page<TransactionResponseDTO> filter(@RequestHeader("X-USER-ID") Long userId,
			@RequestHeader("X-ROLE") Role role, TransactionFilterDTO filter, @RequestParam int page,
			@RequestParam int size) {

		return service.filterTransactions(filter, userId, role, page, size);
	}

	@PutMapping("/{id}")
	public TransactionResponseDTO update(@PathVariable Long id, @RequestHeader("X-ROLE") Role role,
			@Valid @RequestBody TransactionRequestDTO request) {

		return service.updateTransaction(id, request, role);
	}
}