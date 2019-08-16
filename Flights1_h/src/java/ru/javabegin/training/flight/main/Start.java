package ru.javabegin.training.flight.main;

import java.util.ArrayList;
import ru.javabegin.training.flight.interfaces.impls.SearchImpl;
import ru.javabegin.training.flight.interfaces.impls.TestVars;
import ru.javabegin.training.flight.objects.Flight;

public class Start {

    public static void main(String[] args) {

        SearchImpl searchImpl = new SearchImpl();
        ArrayList<Flight> flightList = new ArrayList<>(); 
        flightList.addAll(searchImpl.searchFlight(TestVars.calendar1.getTime(), TestVars.city2, TestVars.city1, 1));
        
        System.out.println(flightList.size());

        

    }
    
    
    
}
