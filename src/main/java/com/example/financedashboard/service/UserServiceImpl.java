package com.example.financedashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.financedashboard.dto.UserRequestDTO;
import com.example.financedashboard.dto.UserResponseDTO;
import com.example.financedashboard.entity.User;
import com.example.financedashboard.exception.ResourceNotFoundException;
import com.example.financedashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	@Override
	public UserResponseDTO create(UserRequestDTO request) {
		User user = User.builder().name(request.getName()).email(request.getEmail()).role(request.getRole())
				.active(request.isActive()).build();

		return map(repository.save(user));
	}

	@Override
	public List<UserResponseDTO> getAll() {
		return repository.findAll().stream().map(this::map).toList();
	}

	@Override
	public UserResponseDTO getById(Long id) {
		return map(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found")));
	}

	@Override
	public UserResponseDTO update(Long id, UserRequestDTO request) {
		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setRole(request.getRole());
		user.setActive(request.isActive());

		return map(repository.save(user));
	}

	@Override
	public void delete(Long id) {
		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		repository.delete(user);
	}

	@Override
	public UserResponseDTO toggleActive(Long id) {
		User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		user.setActive(!user.isActive());
		return map(repository.save(user));
	}

	private UserResponseDTO map(User u) {
		return UserResponseDTO.builder().id(u.getId()).name(u.getName()).email(u.getEmail()).role(u.getRole())
				.active(u.isActive()).build();
	}
}