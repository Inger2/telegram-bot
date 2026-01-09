package com.example.telegram_bot.service;

import com.example.telegram_bot.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserStateService implements UserService {
    private final Map<Long, UserState> userStates = new ConcurrentHashMap<>();
    private final Map<Long, UserDTO> userData = new ConcurrentHashMap<>();

    public UserState getState(Long chatId) {
        return userStates.getOrDefault(chatId, UserState.IDLE);
    }

    public void start(long chatId) {
        userStates.put(chatId, UserState.WAITING_HOME_ADDRESS);
        userData.put(chatId, new UserDTO());
    }

    public void setState(Long chatId, UserState state) {
        userStates.put(chatId, state);
    }

    public UserDTO getData(Long chatId) {
        return userData.get(chatId);
    }
}
