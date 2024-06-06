package com.ssb.car.simulation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GraphDTO {

    private String carName;

    private Integer currentX;

    private Integer currentY;

    private Character currentDirection;

    private int commandIndex;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GraphDTO other))
            return false;
        return other.commandIndex == this.commandIndex &&
                other.currentX.equals(this.currentX) && other.currentY.equals(this.currentY);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.commandIndex, this.currentX, this.currentY);
    }
}
