package com.example.demo.gateways;

import com.example.demo.dtos.MockDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class GetPercentageFromApiGateway implements GetValueGateway {

    @Value("${mock.url}")
    private String mockUrl;

    @Override
    public Long execute() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MockDTO> response = restTemplate.getForEntity(mockUrl, MockDTO.class);
        return Objects.requireNonNull(response.getBody()).getNumber();
    }
}
