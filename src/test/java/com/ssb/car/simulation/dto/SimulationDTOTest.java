package com.ssb.car.simulation.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class SimulationDTOTest {

    @InjectMocks
    SimulationDTO simulationDTO;

    @Test
    @DisplayName("Test to set xMax")
    void testSetXMax(){
        simulationDTO.setXMax(10);
        Assertions.assertEquals(10, simulationDTO.getXMax());
    }

    @Test
    @DisplayName("Test to set yMax")
    void testSetYMax(){
        simulationDTO.setYMax(10);
        Assertions.assertEquals(10, simulationDTO.getYMax());
    }

    @Test
    @DisplayName("Test to set carDTOList")
    void testSetCarDTOList(){
        CarDTO carDTO = CarDTO.builder()
                .carName("A")
                .currentX(1)
                .currentY(2)
                .currentDirection('N')
                .simulationCommand("FFRFFFFRRL").build();
        simulationDTO.setCarDTOList(List.of(carDTO));
        Assertions.assertEquals(1, simulationDTO.getCarDTOList().size());
        Assertions.assertEquals("A", simulationDTO.getCarDTOList().get(0).getCarName());
    }

    @Test
    @DisplayName("Test to set SimulationDTO via All Args Constructor")
    void testSetSimulationDTOViaConstructor(){
        CarDTO carDTO = CarDTO.builder()
                .carName("A")
                .currentX(1)
                .currentY(2)
                .currentDirection('N')
                .simulationCommand("FFRFFFFRRL").build();
        simulationDTO = new SimulationDTO(List.of(carDTO), 10, 10);
        Assertions.assertEquals(10, simulationDTO.getXMax());
        Assertions.assertEquals(10, simulationDTO.getYMax());
        Assertions.assertEquals(1, simulationDTO.getCarDTOList().size());
        Assertions.assertEquals("A", simulationDTO.getCarDTOList().get(0).getCarName());
    }

    @Test
    @DisplayName("Test to set SimulationDTO via Builder")
    void testSetSimulationDTOViaBuilder(){
        CarDTO carDTO = CarDTO.builder()
                .carName("A")
                .currentX(1)
                .currentY(2)
                .currentDirection('N')
                .simulationCommand("FFRFFFFRRL").build();
        simulationDTO = SimulationDTO.builder()
                .xMax(10)
                .yMax(10)
                .carDTOList(List.of(carDTO)).build();
        Assertions.assertEquals(10, simulationDTO.getXMax());
        Assertions.assertEquals(10, simulationDTO.getYMax());
        Assertions.assertEquals(1, simulationDTO.getCarDTOList().size());
        Assertions.assertEquals("A", simulationDTO.getCarDTOList().get(0).getCarName());
    }

}
