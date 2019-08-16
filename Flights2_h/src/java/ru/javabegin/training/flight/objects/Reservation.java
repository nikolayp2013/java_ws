package ru.javabegin.training.flight.objects;

import java.util.Calendar;
import ru.javabegin.training.flight.spr.objects.Place;



public class Reservation {
    
    private Flight flight;

    private Passenger passenger;
    
    private Place place;
    
    private String addInfo;
    
    private Calendar reserveDateTime;
    
    private String code;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public Calendar getReserveDateTime() {
        return reserveDateTime;
    }

    public void setReserveDateTime(Calendar reserveDateTime) {
        this.reserveDateTime = reserveDateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
    

}
