package com.infra.bank.impl;

import com.bank.api.Bank;
import com.dto.*;

import java.util.Random;

public class InvestmentBank implements Bank {

    private final String INVESTMENT_CREDIT_CARD_FIRST_DIGITS = "2001";
    private final String INVESTMENT_DEBIT_CARD_FIRST_DIGITS = "2002";

    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        return switch (cardType) {
            case CREDIT -> new CreditBankCard(INVESTMENT_CREDIT_CARD_FIRST_DIGITS + generateCardLastDigits(), user);
            case DEBIT -> new DebitBankCard(INVESTMENT_DEBIT_CARD_FIRST_DIGITS + generateCardLastDigits(), user);
        };
    }

    private String generateCardLastDigits() {
        var lastDigitsNumber = 1000;

        Random random = new Random();

        var randomInt = random.nextInt(lastDigitsNumber);
        return String.valueOf(randomInt);
    }
}
