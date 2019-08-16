package ru.javabegin.training.flight.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ru.javabegin.training.flight.spr.objects.Country;


public class CountryDB {

    public static Country getCountry(long id) throws SQLException {

        Country country = null;
        Connection conn = AviaDB.getInstance().getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("select * from spr_country where id=?");
            stmt.setLong(1, id);

            rs = stmt.executeQuery();
            rs.first();

            country = new Country();
            country.setId(rs.getLong("id"));
            country.setCode(rs.getString("code"));
            country.setFlag(rs.getBytes("flag"));
            country.setDesc(rs.getString("desc"));
            country.setName(rs.getString("name"));

        } finally {
            if (rs!=null) {
                rs.close();
            }
            if (stmt!=null) {
                stmt.close();
            }
        }


        return country;
    }
}
