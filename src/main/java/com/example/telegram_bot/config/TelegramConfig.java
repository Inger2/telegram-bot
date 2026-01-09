package com.example.telegram_bot.config;

import com.example.telegram_bot.MyTgBot;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
public class TelegramConfig {

    @Bean
    public TelegramClient telegramClient(MyTgBot myTgBot) {
        return new OkHttpTelegramClient(myTgBot.getBotToken());
    }
}
