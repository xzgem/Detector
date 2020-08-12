package com.ryoua.model;

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
public class Contact {
    private Integer id;
    private Integer user;
    private String contact;
    private String typeStr;
    private String updateTime;
    private Long updateTimeMills;
    private Integer valid;
}
