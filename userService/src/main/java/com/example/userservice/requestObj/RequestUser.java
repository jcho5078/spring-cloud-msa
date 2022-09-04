package com.example.userservice.requestObj;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {

    @NotNull
    @Size(min = 2, message = "Name not be less than 2 character")
    private String name;

    @NotNull
    @Size(min = 3, message = "Password must grater then 3 character")
    private String pwd;

    @NotNull
    @Size(min = 1, message = "email is essential value")
    @Email
    private String email;
}
