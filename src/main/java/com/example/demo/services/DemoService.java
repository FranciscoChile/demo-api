package com.example.demo.services;

import com.example.demo.dtos.AverageDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DemoService {

    AverageDTO calcAverage(long num1, long num2);

    List<AverageDTO> findAll(Pageable pageable);

}
