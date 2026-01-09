package com.example.telegram_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
public class TelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);

    }

}
