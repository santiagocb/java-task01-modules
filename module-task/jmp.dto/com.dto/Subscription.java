package com.dto;

import java.time.LocalDate;

public record Subscription(String bankcardNumber, LocalDate startDate) {}
