package com.ssb.car.simulation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDTO {

    @JsonProperty(value = "carName")
    private String carName;

    @JsonProperty(value = "currentX")
    private Integer currentX;

    @JsonProperty(value = "currentY")
    private Integer currentY;

    @JsonProperty(value = "currentDirection")
    private Character currentDirection;

    @JsonProperty(value = "simulationCommand")
    private String simulationCommand;

}
