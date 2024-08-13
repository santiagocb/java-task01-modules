package com.dto;

public record CreditBankCard(String number, User user) implements BankCard {
}
