package com.spring.tutorial.v1_web_app_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerWebApplication.class, args);
	}

//	@Bean
//	CommandLineRunner initData(UserRepository userRepo, ExpenseRepository expenseRepo, LabelRepository labelRepo) {
//		return args -> {
//			User user1 = new User();
//			user1.setName("Alice");
//			user1.setEmail("alice@example.com");
//			user1.setPasswordHash("hashedpwd");
//			userRepo.save(user1);
//
//			Label food = new Label();
//			food.setName("Food");
//			Label travel = new Label();
//			travel.setName("Travel");
//			labelRepo.saveAll(List.of(food, travel));
//
//			Expense expense = new Expense();
//			expense.setAmount(50.0);
//			expense.setDate(LocalDate.now());
//			expense.setDescription("Lunch at cafe");
//			expense.setUser(user1);
//			expense.setLabels(Set.of(food));
//
//			expenseRepo.save(expense);
//		};
//	}

}
