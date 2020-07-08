package com.park.consumer.domain;

import lombok.Data;

/**
 * @author BarryLee
 */
@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String email;
}
