//package com.example.sensor_api.controllers;
//
//import com.example.sensor_api.dto.SensorDTO;
//import com.example.sensor_api.exception.SensorAlreadyExistsException;
//import com.example.sensor_api.services.SensorService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@SpringBootTest
//@AutoConfigureMockMvc
//class SensorControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SensorService sensorService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void registerSensor_success() throws Exception {
//        SensorDTO sensorDTO = new SensorDTO("TestSensor");
//
//        mockMvc.perform(post("/sensors/registration")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(sensorDTO)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Sensor registered successfully."));
//    }
//}
