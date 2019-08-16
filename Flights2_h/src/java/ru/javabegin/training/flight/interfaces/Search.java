package ru.javabegin.training.flight.interfaces;

import java.util.Date;
import java.util.List;
import ru.javabegin.training.flight.spr.objects.City;
import ru.javabegin.training.flight.objects.Flight;



public interface Search {
    
    List<Flight> searchFlight(Date date, City cityFrom, City cityTo, int placeCount);  

}
