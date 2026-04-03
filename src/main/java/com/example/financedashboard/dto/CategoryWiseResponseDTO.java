package com.example.financedashboard.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryWiseResponseDTO {
	private String category;
	private BigDecimal total;
}