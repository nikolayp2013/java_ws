package ru.javabegin.training.flight.spr.objects;

public class Place {

    private long id;
    private char row;
    private int seat;    
    private FlightClass flightClass;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
