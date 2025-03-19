package com.example.demo.controllers;

import com.example.demo.dtos.AverageDTO;
import com.example.demo.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/average")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping
    public ResponseEntity<AverageDTO> calcDynamicAverage(@RequestParam long num1, @RequestParam long num2) {
        return new ResponseEntity<>(demoService.calcAverage(num1, num2), HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<AverageDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(demoService.findAll(pageable), HttpStatus.OK);
    }

}
