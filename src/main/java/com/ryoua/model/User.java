package com.ryoua.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date createTime;
    private Date updateTime;
    private String headPhoto;
    private Boolean isRemember;
}
