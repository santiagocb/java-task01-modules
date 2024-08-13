package com.app;


import com.bank.api.Bank;
import com.dto.BankCardType;
import com.dto.Subscription;
import com.dto.User;
import com.exceptions.ImplementationNotFoundException;
import com.exceptions.SubscriptionNotFoundException;
import com.service.api.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.ServiceLoader;

public class Application {

	private Bank bank;
	private Service service;
	List<User> users;

	private final String TEXT_SEPARATOR = "---------------------------";


	public Application(List<User> users) {

		ServiceLoader<Bank> banks = ServiceLoader.load(Bank.class);
		ServiceLoader<Service> services = ServiceLoader.load(Service.class);

		var bank = banks.findFirst()
				.orElseThrow(() -> new ImplementationNotFoundException("Implementation not found for Bank API"));
		var service = services.findFirst()
				.orElseThrow(() -> new ImplementationNotFoundException("Implementation not found for Service API"));

		this.bank = bank;
		this.service = service;
		this.users = users;
	}

	public void printAllUsers() {
		System.out.println("All subscriber list:");
		service
				.getAllUsers()
				.forEach(u -> System.out.printf("Name: %s %s, Birthday: %s%n", u.name(), u.surname(), u.birthday()));
		System.out.println(TEXT_SEPARATOR);
	}

	public void printSubscriptionOfCurrentMoth() {
		System.out.println("All subscriptions that began current month:");

		var firstDayOfCurrentMonth = LocalDate.now().withDayOfMonth(1);
		service
				.getAllSubscriptionsByCondition(s -> s.startDate().isAfter(firstDayOfCurrentMonth) || s.startDate().equals(firstDayOfCurrentMonth))
				.forEach(s -> System.out.printf("CardNumber: %s started on %s%n", s.bankcardNumber(), s.startDate()));
		System.out.println(TEXT_SEPARATOR);
	}

	public void printAverageUserAge() {
		System.out.printf("The average user age is: %s%n", (int) service.getAverageUsersAge());
		System.out.println(TEXT_SEPARATOR);
	}

	public Subscription printSubscriptionByCardNumber(String cardNumber) throws SubscriptionNotFoundException {
		System.out.printf("Trying to fetch subscription with an unknown cardNumber: %s%n", cardNumber);
		return service
				.getSubscriptionByBankCardNumber(cardNumber)
				.orElseThrow(() -> new SubscriptionNotFoundException("Impossible to fetch that subscription"));
	}

	public void subscribeCreditUsers() {
		var firstUsers = users.stream().limit(users.size() / 2).toList();
		subscribeUsersByCardType(firstUsers, BankCardType.CREDIT);
	}

	public void subscribeDebitUsers() {
		var firstUsers = users.stream().skip(users.size() / 2).toList();
		subscribeUsersByCardType(firstUsers, BankCardType.DEBIT);
	}

	private void subscribeUsersByCardType(List<User> users, BankCardType cardType) {
		for (User user : users) {
			System.out.printf("Welcome %s %s, creating your %s card..%n", user.name(), user.surname(), cardType.name());
			var card = bank.createBankCard(user, BankCardType.CREDIT);
			service.subscribe(card);
			System.out.printf("%s %s %s\n", user.name(), user.surname(), stringifyIsPayable(Service.isPayableUser(user)));
			System.out.println(TEXT_SEPARATOR);
		}
	}

	private String stringifyIsPayable(boolean isPayableUser) {
		return isPayableUser ? "payable" : "[WARNING] is NOT payable";
	}

}