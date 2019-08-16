package ru.javabegin.training.flight.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.javabegin.training.flight.spr.objects.City;


public class CityDB {

    private CityDB() {
    }
    
    
    private static CityDB instance;

    public static CityDB getInstance() {
        if (instance == null) {
            instance = new CityDB();
        }

        return instance;
    }
    
    public ArrayList<City> getAllCities() {
        try {
            return getAllCities(getAllCitiesStmt());
        } catch (SQLException ex) {
            Logger.getLogger(CityDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AviaDB.getInstance().closeConnection();
        }
        return null;
    }

    public City getCity(long id) {
        try {
            return getCity(getCityStmt(id));
        } catch (SQLException ex) {
            Logger.getLogger(CityDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AviaDB.getInstance().closeConnection();
        }
        return null;
    }

    public City getCity(String name) {
        try {
            return getCity(getCityStmt(name));
        } catch (SQLException ex) {
            Logger.getLogger(CityDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AviaDB.getInstance().closeConnection();
        }
        return null;
    }
    
    
     private ArrayList<City> getAllCities(PreparedStatement stmt) throws SQLException {
         
        ArrayList<City> list = new ArrayList<>();
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery();

            while (rs.next()) {        
                list.add(fillCity(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }

        return list;
    }

    private City getCity(PreparedStatement stmt) throws SQLException {

        City city = null;
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery();

            rs.next();
            if (rs.isFirst()) {
                city = fillCity(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }

        return city;
    }

      private City fillCity(ResultSet rs) throws SQLException {
        City city = new City();
        city.setId(rs.getLong("id"));
        city.setCode(rs.getString("code"));
        city.setCountry(CountryDB.getCountry(rs.getLong("country_id")));
        city.setDesc(rs.getString("desc"));
        city.setName(rs.getString("name"));
        return city;
    }
    
    private PreparedStatement getCityStmt(String name) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from spr_city where name=?");
        stmt.setString(1, name);
        return stmt;
    }

    private PreparedStatement getCityStmt(long id) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from spr_city where id=?");
        stmt.setLong(1, id);
        return stmt;
    }
    
    private PreparedStatement getAllCitiesStmt() throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from spr_city");
        return stmt;
    }

  
}
