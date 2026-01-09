package com.example.telegram_bot.commands;

public enum CommandName {
    START("START_COMMAND"),
    SAVE("SAVE_COMMAND");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

}
