package com.example.telegram_bot.service;


import com.example.telegram_bot.client.TelegramClient;
import com.example.telegram_bot.commands.Command;
import com.example.telegram_bot.dto.UserDTO;
import com.example.telegram_bot.events.MessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class UserInputHandler implements UserHandler {
    private final ApplicationEventPublisher applicationEventPublisher;

    private final UserService userService;

    private final TelegramClient telegramClient;

    @Override
    public boolean canHandle(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return false;
        }
        return !update.getMessage().getText().startsWith("/");
    }

    @Override
    public void handle(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            UserState state = userService.getState(chatId);
            UserDTO data = userService.getData(chatId);
            if (state == UserState.WAITING_HOME_ADDRESS) {
                data.setUserAddress(text);
                data.setChatId(chatId);
                userService.setState(chatId, UserState.WAITING_WORK_ADDRESS);
                send(chatId, "Enter work address:");
            } else if (state == UserState.WAITING_WORK_ADDRESS) {
                data.setDestAddress(text);
                userService.setState(chatId, UserState.WAITING_DESIRED_TIME);
                send(chatId, "Enter your desired time in HH:mm format. (For example: 09:00):");
            } else if (state == UserState.WAITING_DESIRED_TIME) {
                data.setArrivalTime(LocalTime.parse(text));
                userService.setState(chatId, UserState.WAITING_TIMEZONE);
                send(chatId, "Enter your timezone. (For example: Europe/London):");
            } else if (state == UserState.WAITING_TIMEZONE) {
                data.setUserTimeZone(ZoneId.of(text));
                userService.setState(chatId, UserState.IDLE);
                send(chatId, "Your data is saved. Wait for notification");
                send(chatId, String.valueOf(data));
                telegramClient.sendUserData(data);
            }

        }
    }

    private void send(long chatId, String text) {
        SendMessage message = SendMessage // Create a message object
                .builder()
                .chatId(chatId)
                .text(text)
                .build();
        applicationEventPublisher.publishEvent(new MessageEvent(this, message));
    }
}
