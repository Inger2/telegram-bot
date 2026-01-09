package com.example.telegram_bot.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private long chatId;
    private String message;
}
