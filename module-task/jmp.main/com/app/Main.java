package com.app;

import com.dto.*;
import com.exceptions.SubscriptionNotFoundException;
import com.service.api.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Application app = new Application(loadUsers());

        app.subscribeCreditUsers();
        app.subscribeDebitUsers();

        app.printAllUsers();
        app.printAverageUserAge();

        app.printSubscriptionOfCurrentMoth();


        // Get a subscription with unknown cardNumber
        try {
            var cardNumber = readSearchableCardNumber();
            var sub = app.printSubscriptionByCardNumber(cardNumber);
            System.out.printf("Subscription found: %s%n", sub);
        } catch (SubscriptionNotFoundException e) {
            System.out.printf("[ERROR] Error while fetching subscription: %s%n", e.getMessage());
            System.out.println("Exiting the program safety..");
        }

    }

    public static String readSearchableCardNumber() {
        System.out.println("Please enter a cardNumber to get a subscription: ");
        Scanner scanner = new Scanner(System.in);

        return scanner.next();
    }

    public static List<User> loadUsers() {
        return List.of(
                new User("Muichiro", "Tokito", LocalDate.of(1998, Month.APRIL, 2)),
                new User("Juan", "Fernandez", LocalDate.of(1990, Month.FEBRUARY, 7)),
                new User("Nicolas", "Maduro", LocalDate.of(2015, Month.JANUARY, 12)),
                new User("Vito", "Corleone", LocalDate.of(1970, Month.OCTOBER, 29)),
                new User("Juan", "Barajas", LocalDate.of(2004, Month.NOVEMBER, 13)),
                new User("Tanjiro", "Kamado", LocalDate.of(2000, Month.DECEMBER, 19)),
                new User("Jose", "Alvarez", LocalDate.of(2010, Month.MAY, 4))
        );
    }

}