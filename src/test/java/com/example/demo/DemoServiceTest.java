package com.example.demo;

import com.example.demo.dtos.AverageDTO;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.gateways.GetValueGateway;
import com.example.demo.services.CachePercentage;
import com.example.demo.services.DemoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DemoServiceTest {

    @Mock
    GetValueGateway getValueGateway;

    @InjectMocks
    DemoServiceImpl demoService;

    @Mock
    CachePercentage cachePercentage;


    @Test
    public void error_api_error_mem_throwException()  {
        when(getValueGateway.execute()).thenThrow(DataNotFoundException.class);

        Exception exception = assertThrows(DataNotFoundException.class, () -> demoService.calcAverage(1L, 1L));
        assertNotNull(exception);
    }

    @Test
    public void error_api_ok_mem_throwException()  {
        cachePercentage = new CachePercentage();
        cachePercentage.setExpiration(new Date());
        cachePercentage.setValue(4L);

        demoService = new DemoServiceImpl("", "30", getValueGateway, null, null, cachePercentage);

        when(getValueGateway.execute()).thenThrow(DataNotFoundException.class);
        AverageDTO averageDTO = demoService.calcAverage(1L, 1L);
        assertNotNull(averageDTO.getResult());
    }
}
