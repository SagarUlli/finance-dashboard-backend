package com.example.financedashboard.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.financedashboard.dto.TransactionFilterDTO;
import com.example.financedashboard.dto.TransactionRequestDTO;
import com.example.financedashboard.dto.TransactionResponseDTO;
import com.example.financedashboard.entity.Role;

public interface TransactionService {

	TransactionResponseDTO createTransaction(TransactionRequestDTO request, Long userId, Role role);

	List<TransactionResponseDTO> getTransactions(Long userId, Role role);

	void deleteTransaction(Long id, Role role);

	Page<TransactionResponseDTO> filterTransactions(TransactionFilterDTO filter, Long userId, Role role, int page,
			int size);

	TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO request, Role role);
}