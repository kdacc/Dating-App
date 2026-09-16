package com.example.datingapp.service;

import org.springframework.stereotype.Component;

@Component
public class CompatibilityCalculator {
    public int calculate(int birthDaySum1, int birthDaySum2) {
        return (birthDaySum1 + birthDaySum2) % 9 + 1;
    }
}