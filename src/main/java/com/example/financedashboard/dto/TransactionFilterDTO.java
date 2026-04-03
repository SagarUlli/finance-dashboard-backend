package com.example.financedashboard.dto;

import java.time.LocalDate;

import com.example.financedashboard.entity.TransactionType;

import lombok.Data;

@Data
public class TransactionFilterDTO {
	private TransactionType type;
	private String category;
	private LocalDate startDate;
	private LocalDate endDate;
}