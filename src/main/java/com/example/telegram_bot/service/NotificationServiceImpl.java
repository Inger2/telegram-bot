package com.example.telegram_bot.service;

import com.example.telegram_bot.MyTgBot;
import com.example.telegram_bot.dto.NotificationDTO;
import com.example.telegram_bot.events.MessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final ApplicationEventPublisher publisher;
    public void sendNotification(long chatId, String text) {
        SendMessage sendMessage = SendMessage // Create a message object
                .builder()
                .chatId(chatId)
                .text(text)
                .build();
        publisher.publishEvent(new MessageEvent(this, sendMessage));
    }

}
