package com.ssb.car.simulation.service;

import com.ssb.car.simulation.dto.SimulationDTO;

import java.util.List;

public interface SimulationService {

    List<String> runCarSimulation(SimulationDTO simulationDTO);

}
