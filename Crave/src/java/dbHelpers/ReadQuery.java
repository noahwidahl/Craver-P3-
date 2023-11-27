package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.FoodItem;
import model.FoodSupplier;

/**
 * Class to handle database read operations for FoodItem records.
 */
public class ReadQuery {
    
    private Connection conn;  // Database connection object
    private ResultSet results; // ResultSet for holding database query results

    // Constructor - Initializes database connection
    public ReadQuery() {
        // Properties object to hold database connection info
        Properties props = new Properties();
        // Input stream to read the dbConn.properties file
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            // Loading properties from the file
            props.load(instr);
        } catch (IOException ex) {
            // Logging an error if properties file cannot be loaded
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Closing the input stream
            instr.close();
        } catch (IOException ex) {
            // Logging an error if input stream cannot be closed
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Retrieving database connection parameters
        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");     
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");    
        try {
            // Loading database driver class
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            // Logging an error if driver class not found
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Establishing a connection to the database
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            // Logging an error if connection fails
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    // Method to execute a read operation on the FoodItem table
    public void doRead() {
        try {
            // SQL query to select all records from FoodItem table
            String query = "SELECT * from FoodItem";

            // Preparing the SQL statement
            PreparedStatement ps = conn.prepareStatement(query);
            // Executing the query and storing the results in 'results'
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            // Logging an error if the read operation fails
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    // Method to generate an HTML table from the query results
    public String getHTMLTable() {
        String table = ""; // Initial empty string for the HTML table
        table += "<table border=1>"; // Starting the table with a border

        try {
            // Iterating through the query results
            while(this.results.next()) {
                // Creating a FoodItem object for each row in the result set
                FoodItem FoodItem = new FoodItem();
                FoodItem.setFoodItemID(this.results.getInt("FoodItemID")); 
                FoodItem.setFoodItemName(this.results.getString("FoodItemName"));
                FoodItem.setPrice(this.results.getBigDecimal("price"));

                // Adding rows and columns to the HTML table for each FoodItem
                table += "<tr>";
                table += "<td>" + FoodItem.getFoodItemID() + "</td>";  
                table += "<td>" + FoodItem.getFoodItemName() + "</td>";
                table += "<td>" + FoodItem.getPrice() + "</td>";
                // Adding update and delete links for each FoodItem
                table += "<td><a href=update?FoodItemID=" + FoodItem.getFoodItemID() + "> Update </a>" +
                         "<a href=delete?FoodItemID=" + FoodItem.getFoodItemID() + "> Delete </a></td>";
                table += "</tr>";
            }
        } catch (SQLException ex) {
            // Logging an error if there is an issue processing the result set
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        table += "</table>"; // Closing the HTML table tag
        
        return table; // Returning the constructed HTML table
    }
}
