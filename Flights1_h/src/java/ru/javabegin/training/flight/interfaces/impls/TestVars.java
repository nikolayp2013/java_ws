package ru.javabegin.training.flight.interfaces.impls;

import java.util.ArrayList;
import java.util.Calendar;
import ru.javabegin.training.flight.objects.Flight;
import ru.javabegin.training.flight.spr.objects.Aircraft;
import ru.javabegin.training.flight.spr.objects.City;
import ru.javabegin.training.flight.spr.objects.Company;
import ru.javabegin.training.flight.spr.objects.Country;
import ru.javabegin.training.flight.spr.objects.FlightClass;
import ru.javabegin.training.flight.spr.objects.Place;



public class TestVars {
    
    public static final Company company1;    
    public static final FlightClass economClass;
    public static final Place place1;
    public static final Place place2;
    public static final Place place3;
    public static final Place place4;
    
    public static final Country country1;
    public static final Country country2;
    
    public static final City city1;
    public static final City city2;
    
    public static final Aircraft aircraft1;
    
    public static final Flight flight1;
    
    public static final  Calendar calendar1;
    public static final  Calendar calendar2;
    
    public static final  ArrayList<Flight> flightList = new ArrayList<>();
    
    public static final ArrayList<Place> places = new ArrayList<>();
    
    static{
        company1 = new Company();
        company1.setId(1);
        company1.setName("America Airlines");


        economClass = new FlightClass();
        economClass.setId(1);
        economClass.setName("Эконом");

        place1 = new Place();
        place1.setId(1);
        place1.setRow('A');
        place1.setSeat(1);
        place1.setFlightClass(economClass);

        place2 = new Place();
        place1.setId(2);
        place1.setRow('A');
        place1.setSeat(2);
        place1.setFlightClass(economClass);


        place3 = new Place();
        place1.setId(3);
        place1.setRow('B');
        place1.setSeat(1);
        place1.setFlightClass(economClass);


        place4 = new Place();
        place1.setId(4);
        place1.setRow('B');
        place1.setSeat(2);
        place1.setFlightClass(economClass);

        
        places.add(place1);
        places.add(place2);
        places.add(place3);
        places.add(place4);


        country1 = new Country();
        country1.setId(1);
        country1.setName("Россия");
        country1.setCode("RUS");


        country2 = new Country();
        country1.setId(2);
        country1.setName("США");
        country1.setCode("USA");

        city1 = new City();
        city1.setCountry(country1);
        city1.setId(1);
        city1.setName("Москва");
        city1.setCode("MOS");

        city2 = new City();
        city2.setCountry(country2);
        city2.setId(2);
        city2.setName("Нью-Йорк");
        city2.setCode("NY");

        aircraft1 = new Aircraft();
        aircraft1.setId(1);
        aircraft1.setCompany(company1);
        aircraft1.setName("Airbus 360");
        aircraft1.setPlaceList(places);

        flight1 = new Flight();
        flight1.setAircraft(aircraft1);
        flight1.setCityFrom(city1);
        flight1.setCityTo(city2);
        flight1.setCode("UF-23");
        flight1.setDuration(55);

        calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(1295968560000L); // 25.01.11 10:16

        calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(1296136800000L);// 27.01.11 09:00


        flight1.setFlightDate(calendar1);
        flight1.setFlightTime(calendar1);

        flightList.add(flight1);
    }

}
