package com.example.financedashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financedashboard.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}