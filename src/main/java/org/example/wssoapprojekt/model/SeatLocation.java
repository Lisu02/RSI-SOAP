package org.example.wssoapprojekt.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SeatLocation {

    private Integer x;
    private Integer y;

    public SeatLocation(Integer x, Integer y){
        if(x > 10 || y > 10){
            throw new RuntimeException("No such seat location");
        }
        this.x = x;
        this.y = y;
    }
}
