package ru.javabegin.training.flight.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.javabegin.training.flight.spr.objects.Company;



public class CompanyDB {
    
      private CompanyDB() {
    }
    private static CompanyDB instance;

    public static CompanyDB getInstance() {
        if (instance == null) {
            instance = new CompanyDB();
        }

        return instance;
    }
    

    public Company getCompany(long id) {
        try {
            return getCompany(getCompanyStmt(id));
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            AviaDB.getInstance().closeConnection();
        }
        return null;
    }

 
    private Company getCompany(PreparedStatement stmt) throws SQLException {

        Company company = null;
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery();

            rs.next();
            if (rs.isFirst()) {
                company = new Company();
                company.setId(rs.getLong("id"));
                company.setName(rs.getString("name"));
                company.setDesc(rs.getString("desc"));
            }

        } finally {
            rs.close();
            stmt.close();
        }

        return company;
    }

    private PreparedStatement getCompanyStmt(long id) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("select * from spr_company where id=?");
        stmt.setLong(1, id);
        return stmt;
    }

}
