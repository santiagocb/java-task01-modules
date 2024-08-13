package com.dto;

public record DebitBankCard(String number, User user) implements BankCard {}
