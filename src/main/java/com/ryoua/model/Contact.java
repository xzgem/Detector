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
    private int id;
    private int user;
    private String contact;
    private int valid;
    private int type;
    private String typeStr;
}
