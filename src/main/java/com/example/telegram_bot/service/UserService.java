package com.example.telegram_bot.service;

import com.example.telegram_bot.dto.UserDTO;

public interface UserService {

    UserState getState(Long chatId);

    void setState(Long chatId, UserState state);

    UserDTO getData(Long chatId);

    void start(long chatId);

}
