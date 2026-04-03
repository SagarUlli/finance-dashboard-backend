package com.example.financedashboard.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.financedashboard.entity.TransactionType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponseDTO {

	private Long id;
	private BigDecimal amount;
	private TransactionType type;
	private String category;
	private LocalDate date;
	private String notes;
	private Long userId;
}