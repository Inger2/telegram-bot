package com.example.telegram_bot.dto;


import lombok.Data;

import java.time.LocalTime;
import java.time.ZoneId;

@Data
public class UserDTO {
    long chatId;

    String userAddress;

    String destAddress;

    ZoneId userTimeZone;

    LocalTime arrivalTime;

}
