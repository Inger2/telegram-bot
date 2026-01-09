package com.example.telegram_bot.controller;

import com.example.telegram_bot.dto.NotificationDTO;
import com.example.telegram_bot.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/getMessage")
    public void getMessage(@RequestBody NotificationDTO notificationDTO) {
        notificationService.sendNotification(notificationDTO.getChatId(),notificationDTO.getMessage());
    }

}
