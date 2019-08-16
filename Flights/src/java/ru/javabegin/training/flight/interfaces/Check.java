package ru.javabegin.training.flight.interfaces;

import ru.javabegin.training.flight.objects.Reservation;



public interface Check {
    
    Reservation checkReservation(String code);

}
