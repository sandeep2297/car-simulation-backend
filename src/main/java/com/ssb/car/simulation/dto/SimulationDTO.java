package com.ssb.car.simulation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimulationDTO {

    private List<CarDTO> carDTOList;

    @JsonProperty(value = "xMax")
    private Integer xMax;

    @JsonProperty(value = "yMax")
    private Integer yMax;
}
