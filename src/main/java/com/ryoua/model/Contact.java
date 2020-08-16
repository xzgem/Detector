package com.ryoua.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("contact")
public class Contact {
    private Integer id;
    private Integer user;
    private String contact;
    private Integer status;
    private Integer type;
    private String updateTime;
    private Long updateTimeMills;
    private Integer valid;
}
