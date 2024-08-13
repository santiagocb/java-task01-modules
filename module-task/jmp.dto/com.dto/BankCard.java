package com.dto;

public sealed interface BankCard permits CreditBankCard, DebitBankCard {

    String number();
    User user();

}
