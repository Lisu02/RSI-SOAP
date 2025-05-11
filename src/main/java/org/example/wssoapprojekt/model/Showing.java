package org.example.wssoapprojekt.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Showing {

    @XmlElement
    private Long showingId;
    @XmlElement(required = true)
    private Movie movie;
    @XmlElement(required = true)
    private String showingDateAndTime;
    @XmlElement
    private Long[][] seats = new Long[10][10];

    public Showing(){
        initSeats();
    }
    public Showing(Movie movie, String date) {
        this.movie = movie;
        this.showingDateAndTime = date;
    }

    private void initSeats(){
        for(int i = 0 ; i < 10; i++){
            for(int j = 0; j < 10; j++){
                seats[i][j] = 0L;
            }
        }
    }

    public void makeSeatReservation(List<SeatLocation> seatLocationList, Long reservationId){
        while (seatLocationList.iterator().hasNext()){
            SeatLocation seatLocation = seatLocationList.iterator().next();
            Integer x = seatLocation.getX();
            Integer y = seatLocation.getY();
            if(seats[x][y] == 0){
                seats[x][y] = reservationId;
            }
        }

    }

    public void removeSpecifiedSeatReservation(Reservation reservation,List<SeatLocation> seatLocationList){
        //todo: dokonczyc usuwanie tylko wybranych miejsc z rezerwacji (nie konieczne do zrobienia)
    }

    public void removeAllSeatReservation(Reservation reservation){
        Long reservationId = reservation.getReservationId();
        for(int i = 0 ; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(Objects.equals(seats[i][j], reservationId)){
                    seats[i][j] = 0L;
                }
            }
        }
    }

}
