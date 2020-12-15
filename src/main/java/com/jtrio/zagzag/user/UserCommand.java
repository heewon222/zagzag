package com.jtrio.zagzag.user;


import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserCommand {
    @Data
    public static class CreateUser{ //이너클래스,생성
        @NotBlank
        private String name;
        @NotBlank
        private String password;
        @Email
        private String email;
        private String address;
        @NotBlank
        private Gender gender;

        public User toUser(){
            User user = new User();
            user.setEmail(email);
            user.setAddress(address);
            user.setGender(gender);
            user.setName(name);
            user.setPassword(password);

            return user;
        }

    }
        @Data
    public static class UpdateUser{  //이너클래스,정보수정
        private String address;
        private String name;
        private Gender gender;

        public User toUser(User user){

            user.setName(name);
            user.setAddress(address);
            user.setGender(gender);

            return user;
        }
    }

}
