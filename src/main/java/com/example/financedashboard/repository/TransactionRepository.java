package com.example.financedashboard.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.financedashboard.entity.Transaction;
import com.example.financedashboard.entity.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByUserId(Long userId);

	@Query("""
			    SELECT SUM(t.amount)
			    FROM Transaction t
			    WHERE t.type = :type AND t.user.id = :userId
			""")
	BigDecimal sumByTypeAndUser(@Param("type") TransactionType type, @Param("userId") Long userId);

	@Query("""
			    SELECT t.category, SUM(t.amount)
			    FROM Transaction t
			    WHERE t.user.id = :userId
			    GROUP BY t.category
			""")
	List<Object[]> categoryWise(@Param("userId") Long userId);

	@Query("""
			    SELECT FUNCTION('DATE_FORMAT', t.date, '%Y-%m'),
			           SUM(CASE WHEN t.type = 'INCOME' THEN t.amount ELSE 0 END),
			           SUM(CASE WHEN t.type = 'EXPENSE' THEN t.amount ELSE 0 END)
			    FROM Transaction t
			    WHERE t.user.id = :userId
			    GROUP BY FUNCTION('DATE_FORMAT', t.date, '%Y-%m')
			    ORDER BY FUNCTION('DATE_FORMAT', t.date, '%Y-%m')
			""")
	List<Object[]> monthlyTrends(@Param("userId") Long userId);

	@Query("""
			    SELECT t FROM Transaction t
			    WHERE t.user.id = :userId
			    ORDER BY t.date DESC
			""")
	List<Transaction> findRecent(@Param("userId") Long userId);

	@Query("""
			    SELECT t FROM Transaction t
			    WHERE (:userId IS NULL OR t.user.id = :userId)
			    AND (:type IS NULL OR t.type = :type)
			    AND (:category IS NULL OR t.category = :category)
			    AND (:startDate IS NULL OR t.date >= :startDate)
			    AND (:endDate IS NULL OR t.date <= :endDate)
			""")
	Page<Transaction> filterTransactions(@Param("userId") Long userId, @Param("type") TransactionType type,
			@Param("category") String category, @Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate, Pageable pageable);
}