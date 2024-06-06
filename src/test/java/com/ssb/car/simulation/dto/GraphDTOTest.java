package com.ssb.car.simulation.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class GraphDTOTest {

    @InjectMocks
    GraphDTO graphDTO;

    @Test
    @DisplayName("Test to set car name")
    void testSetCarName(){
        graphDTO.setCarName("A");
        Assertions.assertEquals("A", graphDTO.getCarName());
    }

    @Test
    @DisplayName("Test to set current X")
    void testSetCurrentX(){
        graphDTO.setCurrentX(1);
        Assertions.assertEquals(1, graphDTO.getCurrentX());
    }

    @Test
    @DisplayName("Test to set current Y")
    void testSetCurrentY(){
        graphDTO.setCurrentY(2);
        Assertions.assertEquals(2, graphDTO.getCurrentY());
    }

    @Test
    @DisplayName("Test to set current direction")
    void testSetCurrentDirection(){
        graphDTO.setCurrentDirection('N');
        Assertions.assertEquals('N', graphDTO.getCurrentDirection());
    }

    @Test
    @DisplayName("Test to set command index")
    void testSetCommandIndex(){
        graphDTO.setCommandIndex(6);
        Assertions.assertEquals(6, graphDTO.getCommandIndex());
    }

    @Test
    @DisplayName("Test to set GraphDTO via All Args Constructor")
    void testSetGraphDTOViaConstructor(){
        graphDTO = new GraphDTO("A", 1, 2, 'N', 6);
        Assertions.assertEquals("A", graphDTO.getCarName());
        Assertions.assertEquals(1, graphDTO.getCurrentX());
        Assertions.assertEquals(2, graphDTO.getCurrentY());
        Assertions.assertEquals('N', graphDTO.getCurrentDirection());
        Assertions.assertEquals(6, graphDTO.getCommandIndex());
    }

    @Test
    @DisplayName("Test to set GraphDTO via Builder")
    void testSetGraphDTOViaBuilder(){
        graphDTO = GraphDTO.builder()
                .carName("A")
                .currentX(1)
                .currentY(2)
                .currentDirection('N')
                .commandIndex(6).build();
        Assertions.assertEquals("A", graphDTO.getCarName());
        Assertions.assertEquals(1, graphDTO.getCurrentX());
        Assertions.assertEquals(2, graphDTO.getCurrentY());
        Assertions.assertEquals('N', graphDTO.getCurrentDirection());
        Assertions.assertEquals(6, graphDTO.getCommandIndex());
    }

    @Test
    @DisplayName("Test to check equals Method in GraphDTO")
    void testGraphDTOEqualsMethod(){
        graphDTO = new GraphDTO("A", 1, 2, 'N', 6);
        GraphDTO graphDTO1 = graphDTO;
        Assertions.assertEquals(graphDTO, graphDTO1);
        CarDTO carDTO = new CarDTO();
        Assertions.assertNotEquals(graphDTO, carDTO);
        GraphDTO graphDTO2 = GraphDTO.builder()
                .carName("A")
                .currentX(1)
                .currentY(2)
                .currentDirection('N')
                .commandIndex(6).build();
        Assertions.assertEquals(graphDTO, graphDTO2);
        GraphDTO graphDTO3 = GraphDTO.builder()
                .carName("A")
                .currentX(2)
                .currentY(2)
                .currentDirection('N')
                .commandIndex(6).build();
        Assertions.assertNotEquals(graphDTO, graphDTO3);
    }

    @Test
    @DisplayName("Test to check equals HashCode in GraphDTO")
    void testGraphDTOHashCodeMethod(){
        graphDTO = new GraphDTO("A", 1, 2, 'N', 6);
        Assertions.assertNotNull(graphDTO.hashCode());
    }

}
