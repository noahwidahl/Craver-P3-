package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import model.FoodItem;

/**
 * Class to handle the database operation of reading a specific FoodItem record by ID.
 */
public class ReadRecord {
    
    private Connection conn; // Database connection object
    private ResultSet results; // ResultSet for holding database query results
    
    private FoodItem FoodItem = new FoodItem(); // FoodItem object to hold the data read from the database
    private int FoodItemID; // ID of the FoodItem to be read

    // Constructor - initializes the class with a specific FoodItemID
    public ReadRecord(int FoodItemID) {
        // Properties object to hold database connection info
        Properties props = new Properties();
        // Input stream to read the dbConn.properties file
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            // Loading properties from the file
            props.load(instr);
        } catch (IOException ex) {
            // Logging an error if properties file cannot be loaded
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Closing the input stream
            instr.close();
        } catch (IOException ex) {
            // Logging an error if input stream cannot be closed
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Retrieving database connection parameters
        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");     
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");

        // Storing the FoodItemID
        this.FoodItemID = FoodItemID;

        try {
            // Loading database driver class
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            // Logging an error if driver class not found
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Establishing a connection to the database
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            // Logging an error if connection fails
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    // Method to execute the read operation
    public void doRead() {
        try {
            // SQL query to select a record from the FoodItem table by ID
            String query = "SELECT * FROM FoodItem WHERE FoodItemID = ?";
            
            // Preparing the SQL statement
            PreparedStatement ps = conn.prepareStatement(query);
            
            // Setting the FoodItemID parameter for the query
            ps.setInt(1, FoodItemID);
            
            // Executing the query
            this.results = ps.executeQuery();
            
            // Moving to the first record in the result set
            this.results.next();
            
            // Setting FoodItem attributes based on the result set
            FoodItem.setFoodItemID(this.results.getInt("FoodItemID"));
            FoodItem.setFoodItemName(this.results.getString("FoodItemName"));
            FoodItem.setPrice(this.results.getBigDecimal("Price"));
        } catch (SQLException ex) {
            // Logging an error if the read operation fails
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Getter method for FoodItem
    public FoodItem getFooditem() {
        return this.FoodItem;
    }
}
