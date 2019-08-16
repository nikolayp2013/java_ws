package ru.javabegin.training.flight.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.javabegin.training.flight.objects.Flight;
import ru.javabegin.training.flight.spr.objects.City;
import ru.javabegin.training.flight.utils.GMTCalendar;

public class FlightDB {
    public static final int INTERVAL = 1;

    private FlightDB() {
    }
    private static FlightDB instance;

    public static FlightDB getInstance() {
        if (instance == null) {
            instance = new FlightDB();
        }

        return instance;
    }

    public Flight getFlight(long id) {
        try {
            return getFlight(getFlightStmt(id));
        } catch (Exception ex) {
            Logger.getLogger(FlightDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AviaDB.getInstance().closeConnection();
        }
        return null;
    }

    
    public ArrayList<Flight> getAllFlights() {
        try {
            return getFlights(getAllFlightsStmt());
        } catch (Exception ex) {
            Logger.getLogger(FlightDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AviaDB.getInstance().closeConnection();
        }
        return null;
    }
    
    public ArrayList<Flight> getFlights(long dateTime, City cityFrom, City cityTo) {
        try {
            Calendar c = GMTCalendar.getInstance();
            c.setTimeInMillis(dateTime);
            return getFlights(getFlightsStmt(c, cityFrom, cityTo));
        } catch (Exception ex) {
            Logger.getLogger(FlightDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AviaDB.getInstance().closeConnection();
        }
        return null;
    }
    
    private ArrayList<Flight> getFlights(PreparedStatement stmt) throws SQLException {

        ArrayList<Flight> list = new ArrayList<>();
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery();

            while(rs.next()){                
                list.add(fillFlight(rs));            
            }
 
        } finally {
            if (rs!=null) rs.close();
            if (stmt!=null) stmt.close();            
        }

        return list;
    }
    
    private Flight getFlight(PreparedStatement stmt) throws SQLException {

        Flight flight = null;
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery();

            rs.next();
            if (rs.isFirst()) {
                flight = fillFlight(rs);
            }
 
        } finally {
            if (rs!=null) rs.close();
            if (stmt!=null) stmt.close();            
        }

        return flight;
    }
    
    
    private static final String MIN = " мин.";
    private static final String HOUR = " ч.  ";
    private static final String DAY = " д.  ";
    
    private Flight fillFlight(ResultSet rs) throws SQLException {
        Calendar dateDepart = GMTCalendar.getInstance();
        Calendar dateCome = GMTCalendar.getInstance();
        
        dateDepart.setTimeInMillis(rs.getLong("date_depart"));
        dateCome.setTimeInMillis(rs.getLong("date_come"));

        Flight flight = new Flight();
        flight.setId(rs.getLong("id"));
        flight.setCode(rs.getString("code"));
        flight.setDateDepart(dateDepart);
        flight.setDateCome(dateCome);
        flight.setAircraft(AircraftDB.getInstance().getAircraft(rs.getLong("aircraft_id")));
        flight.setCityFrom(CityDB.getInstance().getCity(rs.getLong("city_from_id")));
        flight.setCityTo(CityDB.getInstance().getCity(rs.getLong("city_to_id")));
        
        StringBuilder sb = new StringBuilder();
       
        int dayDiff = dateCome.get(Calendar.DAY_OF_YEAR) - dateDepart.get(Calendar.DAY_OF_YEAR);
        int hourDiff = dateCome.get(Calendar.HOUR_OF_DAY) - dateDepart.get(Calendar.HOUR_OF_DAY);
        int minDiff = dateCome.get(Calendar.MINUTE) - dateDepart.get(Calendar.MINUTE);
     
        if (dayDiff > 0) {
            sb.append(dayDiff).append(DAY);
        }

        if (hourDiff > 0) {
            sb.append(hourDiff).append(HOUR);
        }

        if (minDiff > 0) {
            sb.append(minDiff).append(MIN);
        }

        flight.setDuration(sb.toString());

        return flight;
    }

    private PreparedStatement getFlightStmt(long id) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from flight where id=?");
        stmt.setLong(1, id);
        return stmt;
    }
    
      private PreparedStatement getAllFlightsStmt() throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from flight");
        return stmt;
    }
      
    private PreparedStatement getFlightsStmt(Calendar dateTime, City cityFrom, City cityTo) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from flight where date_depart>=? and  date_depart<? and city_from_id=? and city_to_id=?");

//        GMTCalendar.print(dateTime);
    
        // оставить только дату, чтобы искать рейсы за все 24 часа
        dateTime.set(Calendar.HOUR_OF_DAY, 0);
        dateTime.set(Calendar.MINUTE, 0);
        dateTime.set(Calendar.SECOND, 0);
        dateTime.set(Calendar.MILLISECOND, 0);
        

        // в каком интервали искать - в пределах суток или больше
        Calendar dateTimeInterval = (Calendar)(dateTime.clone());
        dateTimeInterval.add(Calendar.DATE, INTERVAL); 
        
        // проверяем интервал
//        GMTCalendar.print(dateTime);
//        GMTCalendar.print(dateTimeInterval);
        
        
        // для проверки запроса
//        System.out.println( dateTime.getTimeInMillis());
//        System.out.println(dateTimeInterval.getTimeInMillis());
//        System.out.println(cityFrom.getId());
//        System.out.println(cityTo.getId());
        
        stmt.setLong(1, dateTime.getTimeInMillis());
        stmt.setLong(2, dateTimeInterval.getTimeInMillis());
        stmt.setLong(3, cityFrom.getId());
        stmt.setLong(4, cityTo.getId());
        return stmt;
    }
}
