package com.example.financedashboard.service;

import java.util.List;

import com.example.financedashboard.dto.CategoryWiseResponseDTO;
import com.example.financedashboard.dto.DashboardSummaryResponseDTO;
import com.example.financedashboard.dto.RecentTransactionDTO;
import com.example.financedashboard.dto.TrendResponseDTO;
import com.example.financedashboard.entity.Role;

public interface DashboardService {

	DashboardSummaryResponseDTO getSummary(Long userId, Role role);

	List<CategoryWiseResponseDTO> getCategoryWise(Long userId, Role role);

	List<TrendResponseDTO> getTrends(Long userId, Role role);

	List<RecentTransactionDTO> getRecentTransactions(Long userId, Role role);
}