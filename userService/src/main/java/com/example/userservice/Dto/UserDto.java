package com.example.userservice.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDto implements Serializable {
    private long id;
    private String name;
    private String userId;
    private String pwd;
    private String email;
    private Date createDate;

    private String encryptedPwd;
}
