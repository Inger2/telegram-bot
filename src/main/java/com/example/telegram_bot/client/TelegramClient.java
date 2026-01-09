package com.example.telegram_bot.client;

import com.example.telegram_bot.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name ="telegramClient",
        url = "localhost:8080/createUser"
)
public interface TelegramClient {
    @PostMapping
    void sendUserData(@RequestBody UserDTO userDTO);
}
