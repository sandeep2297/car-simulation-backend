package com.ssb.car.simulation.service.impl;

import com.ssb.car.simulation.dto.CarDTO;
import com.ssb.car.simulation.dto.GraphDTO;
import com.ssb.car.simulation.dto.SimulationDTO;
import com.ssb.car.simulation.exceptions.NotFoundException;
import com.ssb.car.simulation.service.SimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SimulationServiceImpl implements SimulationService {

    private static final String NORMAL_RESPONSE = "%1$s, (%2$s,%3$s) %4$s";
    private static final String COLLISION_RESPONSE = "%1$s, collides with %2$s at (%3$s,%4$s) at step %5$s";

    public List<String> runCarSimulation(SimulationDTO simulationDTO) {
        Integer xMax = simulationDTO.getXMax();
        Integer yMax = simulationDTO.getYMax();
        List<GraphDTO> graphHistoryList = new ArrayList<>();
        for (CarDTO carDTO : simulationDTO.getCarDTOList()) {
            char[] commandArray = carDTO.getSimulationCommand().toCharArray();
            int commandIndex = 1;
            for (char command : commandArray) {
                if (command == 'F') {
                    updateCarCoordinates(carDTO, xMax, yMax);
                } else if (command == 'R' || command == 'L') {
                    updateCarDirections(carDTO, command);
                }
                GraphDTO graphDTO = GraphDTO.builder()
                        .currentX(carDTO.getCurrentX())
                        .currentY(carDTO.getCurrentY())
                        .commandIndex(commandIndex)
                        .currentDirection(carDTO.getCurrentDirection())
                        .carName(carDTO.getCarName()).build();
                graphHistoryList.add(graphDTO);
                commandIndex++;
            }
        }
        return buildResponse(graphHistoryList, simulationDTO.getCarDTOList());
    }

    private void updateCarCoordinates(CarDTO carDTO, Integer xMax, Integer yMax) {
        switch (carDTO.getCurrentDirection()) {
            case 'N':
                if (carDTO.getCurrentY() + 1 <= yMax - 1) {
                    carDTO.setCurrentY(carDTO.getCurrentY() + 1);
                }
                break;
            case 'S':
                if (carDTO.getCurrentY() - 1 >= 0) {
                    carDTO.setCurrentY(carDTO.getCurrentY() - 1);
                }
                break;
            case 'E':
                if (carDTO.getCurrentX() + 1 <= xMax - 1) {
                    carDTO.setCurrentX(carDTO.getCurrentX() + 1);
                }
                break;
            case 'W':
                if (carDTO.getCurrentX() - 1 >= 0) {
                    carDTO.setCurrentX(carDTO.getCurrentX() - 1);
                }
                break;
            default:
                break;
        }
    }

    private void updateCarDirections(CarDTO carDTO, Character command) {
        switch (carDTO.getCurrentDirection()) {
            case 'N':
                if (command == 'R') {
                    carDTO.setCurrentDirection('E');
                } else if (command == 'L') {
                    carDTO.setCurrentDirection('W');
                }
                break;
            case 'S':
                if (command == 'R') {
                    carDTO.setCurrentDirection('W');
                } else if (command == 'L') {
                    carDTO.setCurrentDirection('E');
                }
                break;
            case 'W':
                if (command == 'R') {
                    carDTO.setCurrentDirection('N');
                } else if (command == 'L') {
                    carDTO.setCurrentDirection('S');
                }
                break;
            case 'E':
                if (command == 'R') {
                    carDTO.setCurrentDirection('S');
                } else if (command == 'L') {
                    carDTO.setCurrentDirection('N');
                }
                break;
            default:
                break;
        }
    }

    private List<String> buildResponse(List<GraphDTO> graphHistoryList, List<CarDTO> carDTOList) {
        List<GraphDTO> collisionCoordinateList = new ArrayList<>();
        List<String> responseList = new ArrayList<>();
        for (GraphDTO graphDTO : graphHistoryList) {
            if (Collections.frequency(graphHistoryList, graphDTO) > 1) {
                collisionCoordinateList.add(graphDTO);
            }
        }
        if (collisionCoordinateList.isEmpty()) {
            for (CarDTO carDTO : carDTOList) {
                responseList.add(buildNormalResponse(graphHistoryList, carDTO));
            }
        } else {
            for (CarDTO carDTO : carDTOList) {
                Optional<GraphDTO> graphDTOOptional = collisionCoordinateList.stream().filter(car -> car.getCarName().equalsIgnoreCase(carDTO.getCarName()))
                        .findFirst();
                if (graphDTOOptional.isEmpty()) {
                    responseList.add(buildNormalResponse(graphHistoryList, carDTO));
                } else {
                    responseList.add(buildCollisionResponse(collisionCoordinateList, graphDTOOptional.get(), carDTO));
                }
            }
        }
        return responseList;
    }

    private String buildNormalResponse(List<GraphDTO> graphHistoryList, CarDTO carDTO) {
        GraphDTO carGraphDTO = graphHistoryList.stream().filter(car ->
                car.getCarName().equalsIgnoreCase(carDTO.getCarName()) &&
                        car.getCommandIndex() == carDTO.getSimulationCommand().length()
        ).findFirst().orElseThrow(() -> new NotFoundException("Last Location Coordinates of " + carDTO.getCarName()));
        return String.format(NORMAL_RESPONSE, carGraphDTO.getCarName(), carGraphDTO.getCurrentX(), carGraphDTO.getCurrentY(), carGraphDTO.getCurrentDirection());
    }

    private String buildCollisionResponse(List<GraphDTO> collisionCoordinateList, GraphDTO graphDTO, CarDTO carDTO) {
        List<String> collidedCarNames = collisionCoordinateList.stream().filter(car -> !(car.getCarName().equalsIgnoreCase(carDTO.getCarName()))
                        && car.getCurrentX().equals(graphDTO.getCurrentX())
                        && car.getCurrentY().equals(graphDTO.getCurrentY())
                        && car.getCommandIndex() == graphDTO.getCommandIndex())
                .map(GraphDTO::getCarName).toList();
        return String.format(COLLISION_RESPONSE, graphDTO.getCarName(), String.join(",", collidedCarNames), graphDTO.getCurrentX(), graphDTO.getCurrentY(), graphDTO.getCommandIndex());
    }

}



