package com.jtrio.zagzag.user;

import com.jtrio.zagzag.execption.EmailDuplicationException;
import com.jtrio.zagzag.execption.UserNotFoundException;
import com.jtrio.zagzag.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDto join(UserCommand.CreateUser command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new EmailDuplicationException("이메일 중복");
        }
        User user=userRepository.save(command.toUser());
        return user.toDto();
    }
    public UserDto update(UserCommand.UpdateUser command,Long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("유저없음"));//찾으면 user, 아니면 예외 throw

         userRepository.save(command.updateUser()); //위에서 user에 save가되는가??

        return user.toDto();
    }
}