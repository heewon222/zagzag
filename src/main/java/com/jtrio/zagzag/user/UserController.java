package com.jtrio.zagzag.user;

import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto join(@Valid @RequestBody UserCommand.CreateUser user) {
        return userService.join(user);
    }

    @PutMapping("/{id}")
    public UserDto update(@AuthenticationPrincipal SecurityUser securityUser, @RequestBody UserCommand.UpdateUser updateUser) {
        User user = userService.findById(securityUser.getUser().getId());
        return userService.update(updateUser, user);
    }
}
