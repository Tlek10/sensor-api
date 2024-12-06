//package com.example.sensor_api.controllers;
//
//import com.example.sensor_api.dto.MeasurementDTO;
//import com.example.sensor_api.dto.SensorDTO;
//import com.example.sensor_api.services.MeasurementService;
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
//import java.util.List;
//
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
//class MeasurementControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MeasurementService measurementService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void addMeasurement_success() throws Exception {
//        MeasurementDTO measurementDTO = new MeasurementDTO(25.5, true, new SensorDTO("TestSensor"));
//
//        mockMvc.perform(post("/measurements/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(measurementDTO)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Measurement added successfully."));
//    }
//
//    @Test
//    void addMeasurement_invalidSensor() throws Exception {
//        MeasurementDTO measurementDTO = new MeasurementDTO(25.5, true, new SensorDTO("UnknownSensor"));
//        Mockito.doThrow(new SensorNotFoundException("Sensor not found."))
//                .when(measurementService).addMeasurement(Mockito.any(MeasurementDTO.class));
//
//        mockMvc.perform(post("/measurements/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(measurementDTO)))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.error").value("Sensor not found."));
//    }
//
//    @Test
//    void getAllMeasurements() throws Exception {
//        List<MeasurementDTO> measurements = Arrays.asList(
//                new MeasurementDTO(20.0, false, new SensorDTO("Sensor1")),
//                new MeasurementDTO(15.0, true, new SensorDTO("Sensor2"))
//        );
//
//        Mockito.when(measurementService.getAllMeasurements()).thenReturn(measurements);
//
//        mockMvc.perform(get("/measurements")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].value").value(20.0))
//                .andExpect(jsonPath("$[0].raining").value(false))
//                .andExpect(jsonPath("$[1].value").value(15.0))
//                .andExpect(jsonPath("$[1].raining").value(true));
//    }
//
//    @Test
//    void getRainyDaysCount() throws Exception {
//        Mockito.when(measurementService.countRainyDays()).thenReturn(5);
//
//        mockMvc.perform(get("/measurements/rainyDaysCount")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.rainyDaysCount").value(5));
//    }
//}
