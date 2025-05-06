package org.example.wssoapprojekt.model;

import org.example.wssoapprojekt.util.CinemaSlots;

public class Seating {

    private Integer[][] sits = CinemaSlots.room;

    public Seating(){}

    public Integer[][] getSitsCopy(){
        Integer[][] copy = new Integer[sits.length][sits[0].length];
        for (int i = 0 ; i < sits.length; i++){
            copy[i] = sits[i].clone();
        }
        return copy;
    }
    public Integer[][] getSits() {return sits;}
    public void setSits(Integer[][] sits) {
        System.out.println("Overwriting sits table!");
        this.sits = sits;
    }

}
