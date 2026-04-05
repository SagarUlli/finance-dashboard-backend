package com.example.financedashboard.service;

import java.util.List;

import com.example.financedashboard.dto.UserRequestDTO;
import com.example.financedashboard.dto.UserResponseDTO;

public interface UserService {

	UserResponseDTO create(UserRequestDTO request);

	List<UserResponseDTO> getAll();

	UserResponseDTO getById(Long id);

	UserResponseDTO update(Long id, UserRequestDTO request);

	void delete(Long id);

	UserResponseDTO toggleActive(Long id);
}