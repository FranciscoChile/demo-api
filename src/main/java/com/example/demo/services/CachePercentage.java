package com.example.demo.services;

import lombok.Data;

import java.util.Date;

@Data
public class CachePercentage {

    private Long value;
    private Date expiration;

}
