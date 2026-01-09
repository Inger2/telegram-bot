package com.example.telegram_bot.commands;

import com.example.telegram_bot.service.UserHandler;
import com.example.telegram_bot.service.UserInputHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CommandHandler {
    private final Collection<Command> commands;
    private final UserHandler userInputHandler;

    public void handle(Update update) {
        for (Command command : commands) {
            if (command.canHandle(update)) {
                command.handle(update);
                return;
            }
        }
        if (userInputHandler.canHandle(update)) {
            userInputHandler.handle(update);
        }
    }
}
