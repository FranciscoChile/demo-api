package com.example.demo.services;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class CachePercentage {

    private Long value;
    private Date expiration;

}
