package com.example.demo.services.mapping;

import com.example.demo.domain.Average;
import com.example.demo.dtos.AverageDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AverageToAverageDTOMapping {

    public List<AverageDTO> mapping (Page<Average> list) {
        List<AverageDTO> averageDTOS = new ArrayList<>();
        for (Average a : list) {
            AverageDTO dto = new AverageDTO();
            dto.setDate(a.getDate());
            dto.setEndpoint(a.getEndpoint());
            dto.setResponse(a.getResponse());
            dto.setParams(a.getParams());
            averageDTOS.add(dto);
        }
        return averageDTOS;
    }

}
