package com.example.datingapp.service;

import org.springframework.stereotype.Component;

@Component
public class NotificationHelper {
    public void notify(String message) {
        System.out.println("Сповіщення: " + message);
    }
}