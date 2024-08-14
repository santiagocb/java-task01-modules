package com.factory;

import com.dto.BankCard;
import com.dto.User;

@FunctionalInterface
public interface BankCardFactory {

    BankCard createBankCard(String number, User user);
}
