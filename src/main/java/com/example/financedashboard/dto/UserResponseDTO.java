package com.example.financedashboard.dto;

import com.example.financedashboard.entity.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
	private Long id;
	private String name;
	private String email;
	private Role role;
	private boolean active;
}