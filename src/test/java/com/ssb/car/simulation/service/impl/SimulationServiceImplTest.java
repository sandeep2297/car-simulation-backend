package com.ssb.car.simulation.service.impl;

import com.ssb.car.simulation.dto.CarDTO;
import com.ssb.car.simulation.dto.SimulationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class SimulationServiceImplTest {

    @InjectMocks
    SimulationServiceImpl simulationServiceImpl;

    CarDTO carDTO = new CarDTO();
    CarDTO carDTO1 = new CarDTO();
    CarDTO carDTO2 = new CarDTO();
    CarDTO carDTO3 = new CarDTO();

    @BeforeEach()
    void setup() {
        carDTO = CarDTO.builder()
                .carName("A")
                .currentX(1)
                .currentY(2)
                .currentDirection('N')
                .simulationCommand("FFRFFFFRRL").build();
        carDTO1 = CarDTO.builder()
                .carName("B")
                .currentX(7)
                .currentY(8)
                .currentDirection('W')
                .simulationCommand("FFLFFFFFFF").build();
        carDTO2 = CarDTO.builder()
                .carName("C")
                .currentX(4)
                .currentY(4)
                .currentDirection('E')
                .simulationCommand("FFLFFFFFFF").build();
        carDTO3 = CarDTO.builder()
                .carName("D")
                .currentX(0)
                .currentY(0)
                .currentDirection('S')
                .simulationCommand("FFRFFFFFFF").build();
    }


    @Test
    @DisplayName("Test to run Simulation with 1 car")
    void testRunCarSimulation() {
        SimulationDTO simulationDTO = SimulationDTO.builder()
                .xMax(10)
                .yMax(10)
                .carDTOList(List.of(carDTO)).build();
        List<String> result = simulationServiceImpl.runCarSimulation(simulationDTO);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("A, (5,4) S", result.get(0));
    }

    @Test
    @DisplayName("Test to run Simulation with 2 car and have collision")
    void testRunCarSimulationWith2CarAndCollision() {
        SimulationDTO simulationDTO = SimulationDTO.builder()
                .xMax(10)
                .yMax(10)
                .carDTOList(List.of(carDTO, carDTO1)).build();
        List<String> result = simulationServiceImpl.runCarSimulation(simulationDTO);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("A, collides with B at (5,4) at step 7", result.get(0));
        Assertions.assertEquals("B, collides with A at (5,4) at step 7", result.get(1));
    }

    @Test
    @DisplayName("Test to run Simulation with 3 car and have 2 collision")
    void testRunCarSimulationWith3CarAnd2Collision() {
        SimulationDTO simulationDTO = SimulationDTO.builder()
                .xMax(10)
                .yMax(10)
                .carDTOList(List.of(carDTO, carDTO1, carDTO2)).build();
        List<String> result = simulationServiceImpl.runCarSimulation(simulationDTO);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("A, collides with B at (5,4) at step 7", result.get(0));
        Assertions.assertEquals("B, collides with A at (5,4) at step 7", result.get(1));
        Assertions.assertEquals("C, (6,9) N", result.get(2));
    }

    @Test
    @DisplayName("Test to run Simulation with 1 car which start at (0,0) S and does not move")
    void testRunCarSimulationWith1CarAndDoesNotMove() {
        SimulationDTO simulationDTO = SimulationDTO.builder()
                .xMax(10)
                .yMax(10)
                .carDTOList(List.of(carDTO3)).build();
        List<String> result = simulationServiceImpl.runCarSimulation(simulationDTO);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("D, (0,0) W", result.get(0));
    }

}
