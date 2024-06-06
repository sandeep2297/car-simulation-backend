package com.ssb.car.simulation.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class CarDTOTest {

    @InjectMocks
    CarDTO carDTO;

    @Test
    @DisplayName("Test to set car name")
    void testSetCarName(){
        carDTO.setCarName("A");
        Assertions.assertEquals("A", carDTO.getCarName());
    }

    @Test
    @DisplayName("Test to set current X")
    void testSetCurrentX(){
        carDTO.setCurrentX(1);
        Assertions.assertEquals(1, carDTO.getCurrentX());
    }

    @Test
    @DisplayName("Test to set current Y")
    void testSetCurrentY(){
        carDTO.setCurrentY(2);
        Assertions.assertEquals(2, carDTO.getCurrentY());
    }

    @Test
    @DisplayName("Test to set current direction")
    void testSetCurrentDirection(){
        carDTO.setCurrentDirection('N');
        Assertions.assertEquals('N', carDTO.getCurrentDirection());
    }

    @Test
    @DisplayName("Test to set simulation command")
    void testSetSimulationCommand(){
        carDTO.setSimulationCommand("FFRFFFFRRL");
        Assertions.assertEquals("FFRFFFFRRL", carDTO.getSimulationCommand());
    }

    @Test
    @DisplayName("Test to set CarDTO via All Args Constructor")
    void testSetCarDTOViaConstructor(){
        carDTO = new CarDTO("A", 1, 2, 'N', "FFRFFFFRRL");
        Assertions.assertEquals("A", carDTO.getCarName());
        Assertions.assertEquals(1, carDTO.getCurrentX());
        Assertions.assertEquals(2, carDTO.getCurrentY());
        Assertions.assertEquals('N', carDTO.getCurrentDirection());
        Assertions.assertEquals("FFRFFFFRRL", carDTO.getSimulationCommand());
    }

    @Test
    @DisplayName("Test to set CarDTO via Builder")
    void testSetCarDTOViaBuilder(){
        carDTO = CarDTO.builder()
                .carName("A")
                .currentX(1)
                .currentY(2)
                .currentDirection('N')
                .simulationCommand("FFRFFFFRRL").build();
        Assertions.assertEquals("A", carDTO.getCarName());
        Assertions.assertEquals(1, carDTO.getCurrentX());
        Assertions.assertEquals(2, carDTO.getCurrentY());
        Assertions.assertEquals('N', carDTO.getCurrentDirection());
        Assertions.assertEquals("FFRFFFFRRL", carDTO.getSimulationCommand());
    }
}
