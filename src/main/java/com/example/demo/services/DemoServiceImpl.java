package com.example.demo.services;

import com.example.demo.domain.Average;
import com.example.demo.dtos.AverageDTO;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.gateways.GetValueGateway;
import com.example.demo.repositories.DemoRepository;
import com.example.demo.services.mapping.AverageToAverageDTOMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DemoServiceImpl implements DemoService {

    private final GetValueGateway getValueGateway;
    private final DemoRepository demoRepository;
    private final String mockUrl;
    private final String expiration;
    private final CachePercentage cachePercentage;
    private String statusResponse;
    private final AverageToAverageDTOMapping averageToAverageDTOMapping;

    public DemoServiceImpl(@Value("${mock.url}") String mockUrl,
                           @Value("${cache.expiration.minutes}") String expiration,
                           GetValueGateway getValueGateway,
                           DemoRepository demoRepository, AverageToAverageDTOMapping averageToAverageDTOMapping, CachePercentage cachePercentage
    ) {
        this.getValueGateway = getValueGateway;
        this.demoRepository = demoRepository;
        this.mockUrl = mockUrl;
        this.expiration = expiration;
        this.averageToAverageDTOMapping = averageToAverageDTOMapping;
        this.cachePercentage = cachePercentage;
    }

    @Override
    public AverageDTO calcAverage(long num1, long num2) {

        Long percentage = 0L;
        try {
            percentage = getValueGateway.execute();
            statusResponse = "OK";
        } catch (DataNotFoundException e) {
            statusResponse = "ERROR";
            if (cachePercentage.getExpiration() != null) {
                percentage = cachePercentage.getValue();
            }
            else {
                throw new DataNotFoundException();
            }
        }

        double result = (num1 + num2) * (((double) percentage / 100) + 1);
        AverageDTO averageDTO = new AverageDTO();
        averageDTO.setResult(result);

        if (cachePercentage.getExpiration() == null) {
            cachePercentage.setValue(percentage);
            cachePercentage.setExpiration(new Date());
        }
        else {
            Date cachedDate = cachePercentage.getExpiration();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cachedDate);
            calendar.add(Calendar.MINUTE, Integer.parseInt(expiration));
            Date cachedPlusExpiration = calendar.getTime();

            Date currentDate = new Date();

            if (currentDate.after(cachedPlusExpiration)) {
                cachePercentage.setExpiration(null);
                cachePercentage.setValue(0L);
            }
        }

        CompletableFuture.runAsync(() -> {
            Average average = new Average();
            average.setDate(new Date());
            average.setEndpoint(mockUrl);
            average.setParams(Arrays.asList(num1, num2));
            average.setResponse(statusResponse);
            demoRepository.save(average);
        });

        return averageDTO;
    }

    @Override
    public List<AverageDTO> findAll(Pageable pageable) {
        Page<Average> list = demoRepository.findAll(pageable);
        return averageToAverageDTOMapping.mapping(list);
    }

}
