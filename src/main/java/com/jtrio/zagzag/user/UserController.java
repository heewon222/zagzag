package com.jtrio.zagzag.user;


import com.jtrio.zagzag.execption.EmailDuplicationException;
import com.jtrio.zagzag.execption.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequiredArgsConstructor
@Controller
@RequestMapping("users")
public class UserController {
    private final UserService userService;

   @PostMapping
    public ResponseEntity<UserDto> join(@Valid @RequestBody UserCommand.CreateUser user) {
        try {
            userService.join(user);
        } catch (EmailDuplicationException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserCommand.UpdateUser user){
        try{
            userService.update(user,id);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
