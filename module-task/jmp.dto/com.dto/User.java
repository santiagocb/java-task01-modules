package com.dto;

import java.time.LocalDate;
import java.time.Period;

public record User(String name, String surname, LocalDate birthday) {

    public int age() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }
}
