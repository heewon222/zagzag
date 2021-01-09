package com.jtrio.zagzag.user;

import com.jtrio.zagzag.execption.EmailDuplicationException;
import com.jtrio.zagzag.execption.UserNotFoundException;
import com.jtrio.zagzag.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto join(UserCommand.CreateUser command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new EmailDuplicationException("email 중복");
        }
        User user = userRepository.save(command.toUser());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserDto.toDto(user);
    }

    @Transactional
    public UserDto update(UserCommand.UpdateUser command, User user) {
        user = userRepository.save(command.toUser(user));

        return UserDto.toDto(user);
    }

    public User findById(Long id){/*Repository 유저정보 찾기*/
        return userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("유저정보 없음"));
    }
}