package com.jtrio.zagzag.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public UserDto join(@Valid @RequestBody UserCommand.CreateUser user) {
        return userService.join(user);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserCommand.UpdateUser user) {
        return userService.update(user, id);
    }
}
