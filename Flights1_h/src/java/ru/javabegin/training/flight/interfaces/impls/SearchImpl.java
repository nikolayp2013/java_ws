package ru.javabegin.training.flight.interfaces.impls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.javabegin.training.flight.interfaces.Search;
import ru.javabegin.training.flight.objects.Flight;
import ru.javabegin.training.flight.spr.objects.City;

public class SearchImpl implements Search {

    

    @Override
    public List<Flight> searchFlight(Date date, City cityFrom, City cityTo, int placeCount) {
        ArrayList<Flight> list = new ArrayList<>();

        for (Flight flight : TestVars.flightList) {
            if (flight.getFlightDate().getTime().getTime() == date.getTime()
                    && flight.getCityFrom().getCode().equals(cityFrom.getCode())
                    && flight.getCityTo().getCode().equals(cityTo.getCode())) {

                if (hasFreePlaces(flight)) {
                    list.add(flight);
                }

            }
        }

        return list;
    }
    
    private boolean hasFreePlaces(Flight flight){
        return true;
    }

    
}
