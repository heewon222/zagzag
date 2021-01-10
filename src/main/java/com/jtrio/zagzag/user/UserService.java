package com.jtrio.zagzag.user;

import com.jtrio.zagzag.execption.EmailDuplicationException;
import com.jtrio.zagzag.execption.UserNotFoundException;
import com.jtrio.zagzag.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserDto join(UserCommand.CreateUser command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new EmailDuplicationException("email 중복");
        }
        User user = userRepository.save(command.toUser());
        return UserDto.toDto(user);
    }

    @Transactional
    public UserDto update(UserCommand.UpdateUser command, Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User 없음"));//찾으면 user, 아니면 예외 throw

        user = userRepository.save(command.toUser(user));

        return UserDto.toDto(user);
    }

    public User findSecurityUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("유저정보 없음"));
    }
}