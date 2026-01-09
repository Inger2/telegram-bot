package com.example.telegram_bot.commands;

import com.example.telegram_bot.events.MessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StartCommand implements Command {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public boolean canHandle(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return false;
        }
        return update.getMessage().getText().equals("/start");
    }

    @Override
    public void handle(Update update)  {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            long chatId = update.getMessage().getChatId();

            SendMessage message = SendMessage // Create a message object
                    .builder()
                    .chatId(chatId)
                    .text("Hello, welcome to Notification Bot.")
                    .build();
            applicationEventPublisher.publishEvent(new MessageEvent(this, message));
        }
    }

    @Override
    public String getCommand() {
        return CommandName.START.getCommandName();
    }
}
