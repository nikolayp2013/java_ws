package ru.javabegin.training.flight.interfaces;

import ru.javabegin.training.flight.objects.Flight;
import ru.javabegin.training.flight.spr.objects.Place;
import ru.javabegin.training.flight.objects.Reservation;



public interface Buy {
    
    Reservation buyTicket(Flight flight, Place place, String addInfo);

}
