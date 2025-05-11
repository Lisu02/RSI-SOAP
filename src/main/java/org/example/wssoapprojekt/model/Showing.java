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

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
public class Showing {

    private Long showingId;
    @XmlElement(required = true)
    private Movie movie;
    private String showingDateAndTime;
    private Integer[][] seats = new Integer[10][10];

    public Showing(){
        initSeats();
    }

    private void initSeats(){
        for(int i = 0 ; i < 10; i++){
            for(int j = 0; j < 10; j++){
                seats[i][j] = 0;
            }
        }
    }



}
