package com.example.financedashboard.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrendResponseDTO {
	private String period;
	private BigDecimal totalIncome;
	private BigDecimal totalExpense;
}