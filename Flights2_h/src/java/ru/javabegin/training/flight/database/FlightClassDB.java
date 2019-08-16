package ru.javabegin.training.flight.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.javabegin.training.flight.spr.objects.FlightClass;

public class FlightClassDB {

    private FlightClassDB() {
    }
    private static FlightClassDB instance;

    public static FlightClassDB getInstance() {
        if (instance == null) {
            instance = new FlightClassDB();
        }

        return instance;
    }
    
    
     public FlightClass getFlightClass(long id) {
        try {
            return getFlightClass(getFlightClassStmt(id));
        } catch (SQLException ex) {
            Logger.getLogger(FlightClassDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AviaDB.getInstance().closeConnection();
        }
        return null;
    }
     
     
     private FlightClass getFlightClass(PreparedStatement stmt) throws SQLException {

        FlightClass flightClass = null;
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery();

            rs.next();
            if (rs.isFirst()) {
                flightClass = new FlightClass();
                flightClass.setId(rs.getLong("id"));
                flightClass.setName(rs.getString("name"));
                flightClass.setDesc(rs.getString("desc"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }

        return flightClass;
    }

   
    private PreparedStatement getFlightClassStmt(long id) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from spr_flight_class where id=?");
        stmt.setLong(1, id);
        return stmt;
    }
}
