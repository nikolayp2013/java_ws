package ru.javabegin.training.flight.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.javabegin.training.flight.spr.objects.Aircraft;



public class AircraftDB {
    
     private AircraftDB() {
    }
    private static AircraftDB instance;

    public static AircraftDB getInstance() {
        if (instance == null) {
            instance = new AircraftDB();
        }

        return instance;
    }
    

    public Aircraft getAircraft(long id) {
        try {
            return getAircraft(getAircraftStmt(id));
        } catch (SQLException ex) {
            Logger.getLogger(AircraftDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AviaDB.getInstance().closeConnection();
        }
        return null;
    }

 
    private Aircraft getAircraft(PreparedStatement stmt) throws SQLException {

        Aircraft aircraft = null;
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery();

            rs.next();
            if (rs.isFirst()) {
                aircraft = new Aircraft();
                aircraft.setId(rs.getLong("id"));
                aircraft.setDesc(rs.getString("desc"));
                aircraft.setName(rs.getString("name"));
                aircraft.setPlaceList(PlaceDB.getInstance().getPlacesByAircraft(rs.getLong("id")));
                aircraft.setCompany(CompanyDB.getInstance().getCompany(rs.getInt("company_id")));
            }
        } finally {
            rs.close();
            stmt.close();
        }

        return aircraft;
    }

    private PreparedStatement getAircraftStmt(long id) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from spr_aircraft where id=?");
        stmt.setLong(1, id);
        return stmt;
    }

}
