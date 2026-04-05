package com.example.financedashboard.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.financedashboard.dto.CategoryWiseResponseDTO;
import com.example.financedashboard.dto.DashboardSummaryResponseDTO;
import com.example.financedashboard.dto.RecentTransactionDTO;
import com.example.financedashboard.dto.TrendResponseDTO;
import com.example.financedashboard.entity.Role;
import com.example.financedashboard.entity.TransactionType;
import com.example.financedashboard.exception.UnauthorizedException;
import com.example.financedashboard.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

	private final TransactionRepository repo;

	private void validateAccess(Role role) {
		if (role == null) {
			throw new UnauthorizedException("Invalid role");
		}
	}

	@Override
	public DashboardSummaryResponseDTO getSummary(Long userId, Role role) {
		validateAccess(role);

		BigDecimal income = repo.sumByTypeAndUser(TransactionType.INCOME, userId);
		BigDecimal expense = repo.sumByTypeAndUser(TransactionType.EXPENSE, userId);

		income = income != null ? income : BigDecimal.ZERO;
		expense = expense != null ? expense : BigDecimal.ZERO;

		return DashboardSummaryResponseDTO.builder().totalIncome(income).totalExpense(expense)
				.netBalance(income.subtract(expense)).build();
	}

	@Override
	public List<CategoryWiseResponseDTO> getCategoryWise(Long userId, Role role) {
		validateAccess(role);

		return repo.categoryWise(userId).stream()
				.map(obj -> new CategoryWiseResponseDTO((String) obj[0], (BigDecimal) obj[1])).toList();
	}

	@Override
	public List<TrendResponseDTO> getTrends(Long userId, Role role) {
		validateAccess(role);

		return repo.monthlyTrends(userId).stream()
				.map(obj -> new TrendResponseDTO(obj[0] + "-" + obj[1], (BigDecimal) obj[2], (BigDecimal) obj[3]))
				.toList();
	}	

	@Override
	public List<RecentTransactionDTO> getRecentTransactions(Long userId, Role role) {
		validateAccess(role);

		return repo
				.findRecent(userId).stream().limit(5).map(t -> RecentTransactionDTO.builder().id(t.getId())
						.amount(t.getAmount()).type(t.getType()).category(t.getCategory()).date(t.getDate()).build())
				.toList();
	}
}