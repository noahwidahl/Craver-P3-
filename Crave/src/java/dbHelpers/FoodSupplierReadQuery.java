package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FoodSupplier;

public class FoodSupplierReadQuery {
    
    private Connection conn;
    private ResultSet results;

    public FoodSupplierReadQuery() {
        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(FoodSupplierReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FoodSupplierReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    public void doRead() {
        try {
            String query = "SELECT * FROM Foodsupplier";
            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplierReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public String getHTMLTable() {
        StringBuilder table = new StringBuilder();
        table.append("<table border='1'>");

        try {
            while (this.results != null && this.results.next()) {
                FoodSupplier supplier = new FoodSupplier(
                    this.results.getInt("FoodsupplierID"),
                    this.results.getString("FoodsupplierName"),
                    this.results.getString("Address"),
                    this.results.getString("PostNr"),
                    this.results.getString("City"),
                    this.results.getString("PhoneNumber"),
                    this.results.getString("ExternalLink"),
                    this.results.getInt("StateID"),
                    this.results.getTimestamp("AddDate"),
                    this.results.getTimestamp("ModifiedDate"),
                    this.results.getBigDecimal("Latitude"),
                    this.results.getBigDecimal("Longitude"),
                    this.results.getInt("FoodSupplierCategoryID")
                );

                table.append("<tr>");
                table.append("<td>").append(supplier.getFoodSupplierID()).append("</td>");
                table.append("<td><a href='read?supplierId=")
                      .append(supplier.getFoodSupplierID())
                      .append("'>")
                      .append(supplier.getFoodSupplierName())
                      .append("</a></td>");
                // Append other fields here
                table.append("</tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplierReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.append("</table>");
        return table.toString();
        
     }
}


