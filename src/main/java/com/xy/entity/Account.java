package com.xy.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xieyong
 * @date 2019/11/7 - 15:01
 */
@Data
public class Account implements Serializable {

    private Integer id;
    private String name;
    private Double money;
}
