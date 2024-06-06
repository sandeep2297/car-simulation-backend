package com.ssb.car.simulation.controller;

import com.ssb.car.simulation.dto.Response;
import com.ssb.car.simulation.dto.SimulationDTO;
import com.ssb.car.simulation.service.SimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulation")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SimulationController {

    private final SimulationService simulationService;

    @PostMapping("/run-simulation")
    public ResponseEntity<Response> runCarSimulation(@RequestBody SimulationDTO simulationDTO) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.value(), simulationService.runCarSimulation(simulationDTO)), HttpStatus.OK);
    }

}
