package com.example.telegram_bot.commands;

import com.example.telegram_bot.events.MessageEvent;
import com.example.telegram_bot.service.UserService;
import com.example.telegram_bot.service.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class SaveCommand implements Command {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserService userService;

    @Override
    public boolean canHandle(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return false;
        }
        return update.getMessage().getText().equals("/save");
    }

    @Override
    public void handle(Update update)  {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            long chatId = update.getMessage().getChatId();
             userService.start(chatId);
            SendMessage message = SendMessage // Create a message object
                    .builder()
                    .chatId(chatId)
                    .text("Enter your home address:")
                    .build();
            applicationEventPublisher.publishEvent(new MessageEvent(this, message));
        }
    }

    @Override
    public String getCommand() {
        return CommandName.SAVE.getCommandName();
    }
}
