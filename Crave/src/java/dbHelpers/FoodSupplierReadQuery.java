package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FoodSupplier;

public class FoodSupplierReadQuery {
    
    private Connection conn; // Variable for database connection
    private ResultSet results; // Variable to hold query results

    // Constructor for initializing the FoodSupplierReadQuery object
    public FoodSupplierReadQuery() {
        Properties props = new Properties(); // Object to hold database properties
        InputStream instr = getClass().getResourceAsStream("dbConn.properties"); // Reading database configuration file
        try {
            props.load(instr); // Load properties from the file
            instr.close(); // Close the input stream
        } catch (IOException ex) {
            // Logging an error if properties file cannot be loaded
            Logger.getLogger(FoodSupplierReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Retrieving database connection parameters
        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");

        try {
            Class.forName(driver); // Loading database driver class
            conn = DriverManager.getConnection(url, username, passwd); // Establishing a connection to the database
        } catch (ClassNotFoundException | SQLException ex) {
            // Logging an error if connection fails
            Logger.getLogger(FoodSupplierReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    // Method to perform the database read operation for FoodSuppliers
    public void doRead() {
        try {
            String query = "SELECT * FROM Foodsupplier"; // SQL query to fetch all FoodSuppliers
            PreparedStatement ps = conn.prepareStatement(query); // Preparing the SQL statement
            this.results = ps.executeQuery(); // Executing the query
        } catch (SQLException ex) {
            // Logging any SQL exceptions that occur during the read operation
            Logger.getLogger(FoodSupplierReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to generate an HTML table representation of the query results
    public String getHTMLTable() {
        StringBuilder table = new StringBuilder(); // StringBuilder to create HTML table
        table.append("<table border='1'>"); // Start of HTML table

        try {
            // Iterate through the ResultSet and populate the HTML table
            while (this.results != null && this.results.next()) {
                // Creating a FoodSupplier object from the current row in the ResultSet
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

                // Append a row to the HTML table for each FoodSupplier
                table.append("<tr>");
                table.append("<td>").append(supplier.getFoodSupplierID()).append("</td>");
                table.append("<td><a href='foodSuppliersFoodItems?supplierId=")
                      .append(supplier.getFoodSupplierID())
                      .append("'>")
                      .append(supplier.getFoodSupplierName())
                      .append("</a></td>");
                // Append other fields here
                table.append("</tr>");
            }
        } catch (SQLException ex) {
            // Logging any SQL exceptions that occur while processing the ResultSet
            Logger.getLogger(FoodSupplierReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.append("</table>"); // End of HTML table
        return table.toString(); // Return the HTML table as a string
    }
}
