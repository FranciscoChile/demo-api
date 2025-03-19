package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AverageDTO {

    private Date date;
    private String endpoint;
    private List<Long> params;
    private String response;

    private Double result;

}
