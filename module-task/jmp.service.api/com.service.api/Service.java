package com.service.api;

import com.dto.BankCard;
import com.dto.Subscription;
import com.dto.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    static boolean isPayableUser(User user) {
        return user.age() > 18;
    }

    default double getAverageUsersAge() {
        return getAllUsers()
                .stream()
                .mapToDouble(User::age)
                .average()
                .orElseThrow();
    }

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);

}
