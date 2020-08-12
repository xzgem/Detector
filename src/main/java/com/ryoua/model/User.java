package com.ryoua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date createTime;
    private Date updateTime;
    private Integer valid;
}
