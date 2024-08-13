package com.infra.service.impl;

import com.dto.BankCard;
import com.dto.Subscription;
import com.dto.User;
import com.service.api.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class CloudService implements Service {

    private final HashMap<BankCard, Subscription> subscriptions;

    public CloudService() {
        subscriptions = new HashMap<>();
    }

    @Override
    public void subscribe(BankCard bankCard) {
        var subscription = new Subscription(bankCard.number(), LocalDate.now());
        subscriptions.put(bankCard, subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return subscriptions
                .values()
                .stream()
                .filter(s -> s.bankcardNumber().equals(cardNumber))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return subscriptions
                .keySet()
                .stream()
                .map(BankCard::user)
                .distinct()
                .toList(); // Replace Collectors.toUnmodifiableList() from Java 16
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return subscriptions.values().stream().filter(predicate).toList();
    }
}
