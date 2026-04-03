package com.example.financedashboard.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.financedashboard.dto.TransactionFilterDTO;
import com.example.financedashboard.dto.TransactionRequestDTO;
import com.example.financedashboard.dto.TransactionResponseDTO;
import com.example.financedashboard.entity.Role;
import com.example.financedashboard.entity.Transaction;
import com.example.financedashboard.entity.User;
import com.example.financedashboard.exception.ResourceNotFoundException;
import com.example.financedashboard.exception.UnauthorizedException;
import com.example.financedashboard.repository.TransactionRepository;
import com.example.financedashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	private final UserRepository userRepository;

	@Override
	public TransactionResponseDTO createTransaction(TransactionRequestDTO request, Long userId, Role role) {

		if (role != Role.ADMIN) {
			throw new UnauthorizedException("Only ADMIN can create transactions");
		}

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Transaction transaction = Transaction.builder().amount(request.getAmount()).type(request.getType())
				.category(request.getCategory()).date(request.getDate()).notes(request.getNotes()).user(user).build();

		transaction = transactionRepository.save(transaction);

		return mapToDTO(transaction);
	}

	@Override
	public List<TransactionResponseDTO> getTransactions(Long userId, Role role) {

		if (role == Role.VIEWER) {
			throw new UnauthorizedException("Viewer cannot access transactions");
		}

		List<Transaction> transactions = (role == Role.ADMIN) ? transactionRepository.findAll()
				: transactionRepository.findByUserId(userId);

		return transactions.stream().map(this::mapToDTO).toList();
	}

	@Override
	public void deleteTransaction(Long id, Role role) {

		if (role != Role.ADMIN) {
			throw new UnauthorizedException("Only ADMIN can delete transactions");
		}

		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

		transactionRepository.delete(transaction);
	}

	@Override
	public Page<TransactionResponseDTO> filterTransactions(TransactionFilterDTO filter, Long userId, Role role,
			int page, int size) {

		Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());

		Long filterUserId = (role == Role.ADMIN) ? null : userId;

		Page<Transaction> result = transactionRepository.filterTransactions(filterUserId, filter.getType(),
				filter.getCategory(), filter.getStartDate(), filter.getEndDate(), pageable);

		return result.map(this::mapToDTO);
	}

	@Override
	public TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO request, Role role) {

		if (role != Role.ADMIN) {
			throw new UnauthorizedException("Only ADMIN can update");
		}

		Transaction t = transactionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

		t.setAmount(request.getAmount());
		t.setType(request.getType());
		t.setCategory(request.getCategory());
		t.setDate(request.getDate());
		t.setNotes(request.getNotes());

		t = transactionRepository.save(t);

		return mapToDTO(t);
	}

	private TransactionResponseDTO mapToDTO(Transaction t) {
		return TransactionResponseDTO.builder().id(t.getId()).amount(t.getAmount()).type(t.getType())
				.category(t.getCategory()).date(t.getDate()).notes(t.getNotes()).userId(t.getUser().getId()).build();
	}
}