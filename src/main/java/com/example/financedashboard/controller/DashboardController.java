package com.example.financedashboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financedashboard.dto.CategoryWiseResponseDTO;
import com.example.financedashboard.dto.DashboardSummaryResponseDTO;
import com.example.financedashboard.dto.RecentTransactionDTO;
import com.example.financedashboard.dto.TrendResponseDTO;
import com.example.financedashboard.entity.Role;
import com.example.financedashboard.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

	private final DashboardService service;

	@GetMapping("/summary")
	public DashboardSummaryResponseDTO getSummary(@RequestHeader("X-USER-ID") Long userId,
			@RequestHeader("X-ROLE") Role role) {

		return service.getSummary(userId, role);
	}

	@GetMapping("/category-wise")
	public List<CategoryWiseResponseDTO> getCategoryWise(@RequestHeader("X-USER-ID") Long userId,
			@RequestHeader("X-ROLE") Role role) {

		return service.getCategoryWise(userId, role);
	}

	@GetMapping("/trends")
	public List<TrendResponseDTO> getTrends(@RequestHeader("X-USER-ID") Long userId,
			@RequestHeader("X-ROLE") Role role) {

		return service.getTrends(userId, role);
	}

	@GetMapping("/recent")
	public List<RecentTransactionDTO> getRecent(@RequestHeader("X-USER-ID") Long userId,
			@RequestHeader("X-ROLE") Role role) {

		return service.getRecentTransactions(userId, role);
	}
}