package com.example.financedashboard.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.financedashboard.entity.Role;
import com.example.financedashboard.entity.User;
import com.example.financedashboard.repository.UserRepository;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			if (userRepository.count() == 0) {

				User admin = User.builder().name("Admin User").email("admin@test.com") // ✅ FIX
						.role(Role.ADMIN).active(true).build();

				User analyst = User.builder().name("Analyst User").email("analyst@test.com") // ✅ FIX
						.role(Role.ANALYST).active(true).build();

				userRepository.save(admin);
				userRepository.save(analyst);
			}
		};
	}
}