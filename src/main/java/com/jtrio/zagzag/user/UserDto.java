package com.jtrio.zagzag.user;

import com.jtrio.zagzag.enums.Gender;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String address;
    private Gender gender;
    private String email;
}
