package com.example.telegram_bot.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UserHandler {

    boolean canHandle(Update update);

    void handle(Update update);
}
