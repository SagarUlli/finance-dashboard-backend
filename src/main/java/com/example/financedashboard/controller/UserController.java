package com.example.financedashboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financedashboard.dto.UserRequestDTO;
import com.example.financedashboard.dto.UserResponseDTO;
import com.example.financedashboard.entity.Role;
import com.example.financedashboard.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService service;

	@PostMapping
	public UserResponseDTO create(@RequestHeader("X-ROLE") Role role, @Valid @RequestBody UserRequestDTO request) {

		if (role != Role.ADMIN) {
			throw new RuntimeException("Only ADMIN can create users");
		}
		return service.create(request);
	}

	@GetMapping
	public List<UserResponseDTO> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public UserResponseDTO getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PutMapping("/{id}")
	public UserResponseDTO update(@RequestHeader("X-ROLE") Role role, @PathVariable Long id,
			@Valid @RequestBody UserRequestDTO request) {

		if (role != Role.ADMIN) {
			throw new RuntimeException("Only ADMIN can update users");
		}
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	public void delete(@RequestHeader("X-ROLE") Role role, @PathVariable Long id) {

		if (role != Role.ADMIN) {
			throw new RuntimeException("Only ADMIN can delete users");
		}
		service.delete(id);
	}

	@PatchMapping("/{id}/toggle-active")
	public UserResponseDTO toggleActive(@RequestHeader("X-ROLE") Role role, @PathVariable Long id) {

		if (role != Role.ADMIN) {
			throw new RuntimeException("Only ADMIN can change status");
		}
		return service.toggleActive(id);
	}
}