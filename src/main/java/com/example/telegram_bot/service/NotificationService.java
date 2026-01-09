package com.example.telegram_bot.service;

public interface NotificationService {
    void sendNotification(long chatId, String text);
}
