package com.ssb.car.simulation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssb.car.simulation.dto.CarDTO;
import com.ssb.car.simulation.dto.SimulationDTO;
import com.ssb.car.simulation.exceptions.NotFoundException;
import com.ssb.car.simulation.service.SimulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class SimulationControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    SimulationController simulationController;

    @Mock
    SimulationService simulationService;

    private MockMvc mockMvc;

    @BeforeEach()
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(simulationController).build();
    }

    @Test
    @DisplayName("Test to verify Endpoint to run simulation")
    void testRunCarSimulation() throws Exception {
        String uri = "/simulation/run-simulation";
        String responseString = "A, (5,4) S";
        CarDTO carDTO = CarDTO.builder()
                .carName("A")
                .currentX(1)
                .currentY(2)
                .currentDirection('N')
                .simulationCommand("FFRFFFFRRL").build();
        SimulationDTO simulationDTO = SimulationDTO.builder()
                .xMax(10)
                .yMax(10)
                .carDTOList(List.of(carDTO))
                .build();
        when(simulationService.runCarSimulation(any())).thenReturn(List.of(responseString));
        String requestJson = mapper.writeValueAsString(simulationDTO);
        mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(requestJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("statusCode", is(200)))
                .andExpect(jsonPath("data", hasSize(1)))
                .andExpect(jsonPath("data[0]", is(responseString)));
    }

}
