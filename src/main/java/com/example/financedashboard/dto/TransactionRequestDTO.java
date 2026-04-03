package com.example.financedashboard.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.financedashboard.entity.TransactionType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionRequestDTO {

	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must be positive")
	private BigDecimal amount;

	@NotNull(message = "Type is required")
	private TransactionType type;

	private String category;

	private LocalDate date;

	private String notes;
}