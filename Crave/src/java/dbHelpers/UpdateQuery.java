package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FoodItem;

/**
 * Class to handle database operations related to updating FoodItem records.
 */
public class UpdateQuery {
    
    private Connection conn; // Database connection object
    
    // Constructor - Initializes database connection
    public UpdateQuery() {
        // Properties object to hold database connection info
        Properties props = new Properties();
        // Input stream to read the dbConn.properties file
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            // Loading properties from the file
            props.load(instr);
            // Closing the input stream
            instr.close();

            // Retrieving database connection parameters
            String driver = props.getProperty("driver.name");
            String url = props.getProperty("server.name");     
            String username = props.getProperty("user.name");
            String passwd = props.getProperty("user.password");    

            // Loading database driver class
            Class.forName(driver);
            // Establishing a connection to the database
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            // Logging an error if any part of the initialization fails
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Method to update a FoodItem in the database
    public void doUpdate(FoodItem FoodItem) {
        try {
            // SQL query to update a record in the FoodItem table
            String query = "UPDATE FoodItem SET FoodItemName = ?, Price = ? WHERE FoodItemID = ?";
            
            // Preparing the SQL statement
            PreparedStatement ps = conn.prepareStatement(query);
            
            // Setting parameters for the update query
            ps.setString(1, FoodItem.getFoodItemName());
            ps.setBigDecimal(2, FoodItem.getPrice());
            // Setting the FoodItemID as the identifier for which record to update
            ps.setInt(3, FoodItem.getFoodItemID());

            // Executing the update
            ps.executeUpdate();
        } catch (SQLException ex) {
            // Logging an error if the update operation fails
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources in a finally block to ensure they are closed
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                // Logging an error if closing the connection fails
                Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
