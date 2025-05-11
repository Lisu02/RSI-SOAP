package org.example.wssoapprojekt.model;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private Long reservationId;
    private Long showingId;
    @XmlElementWrapper(name = "seatLocationList")
    @XmlElement(name = "seatLocation")
    private List<SeatLocation> seatLocation;
    public Reservation(List<SeatLocation> seatLocation) {
        this.seatLocation = seatLocation;
    }
}
