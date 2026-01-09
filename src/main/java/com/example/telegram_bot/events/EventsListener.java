package com.example.telegram_bot.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class EventsListener {
    private final TelegramClient telegramClient;

    @EventListener
    public void onMessageReceived(MessageEvent messageEvent) throws TelegramApiException {
        BotApiMethod<? extends Serializable> message = (BotApiMethod<? extends Serializable>) messageEvent.getMessage();
        telegramClient.execute(message);
    }
}
