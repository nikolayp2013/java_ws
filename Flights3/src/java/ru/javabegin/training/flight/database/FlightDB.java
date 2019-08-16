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

public class FlightDB {

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
        } catch (SQLException ex) {
            Logger.getLogger(FlightDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AviaDB.getInstance().closeConnection();
        }
        return null;
    }

    
    public ArrayList<Flight> getAllFlights() {
        try {
            return getFlights(getAllFlightsStmt());
        } catch (SQLException ex) {
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
            rs.close();
            stmt.close();            
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
            rs.close();
            stmt.close();
        }

        return flight;
    }

    private Flight fillFlight(ResultSet rs) throws SQLException {
        Calendar dateDepart = Calendar.getInstance();
        Calendar dateCome = Calendar.getInstance();

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
      
   
}
