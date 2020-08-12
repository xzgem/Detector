package com.ryoua.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("a_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date createTime;
    private Date updateTime;
    private Integer valid;
}
