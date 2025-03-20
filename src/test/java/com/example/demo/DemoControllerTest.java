package com.example.demo;

import com.example.demo.controllers.DemoController;
import com.example.demo.dtos.AverageDTO;
import com.example.demo.services.DemoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class DemoControllerTest {

	private MockMvc mvc;

	@InjectMocks
	DemoController demoController;

	@Mock
	DemoService demoService;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(demoController).build();
	}

	@Test
	public void average_thenOK() throws Exception {
		mvc.perform(get("/average?num1=8&num2=8")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk() );
	}


	@Test
	public void history_whenGetMethod()
			throws Exception {

		AverageDTO averageDTO = new AverageDTO();
		averageDTO.setDate(new Date());
		averageDTO.setEndpoint("endpoint");
		averageDTO.setResult(5.5);
		averageDTO.setParams(List.of(4L, 4L));
		List<AverageDTO> averageDTOS = List.of(averageDTO);
		when(demoService.findAll(Mockito.any())).thenReturn(averageDTOS);

		mvc.perform(get("/average/history")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.size()",
						is( (averageDTOS).size()  ) ));
	}
}
