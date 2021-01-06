package com.jtrio.zagzag.user;

import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.User;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String address;
    private Gender gender;
    private String email;

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setAddress(user.getAddress());
        userDto.setName(user.getName());
        userDto.setGender(user.getGender());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
