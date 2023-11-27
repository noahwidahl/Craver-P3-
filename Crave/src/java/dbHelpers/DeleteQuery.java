/*
 * Class to handle database operations related to deleting FoodItem records.
 */
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

public class DeleteQuery {
    
    private Connection conn; // Database connection object

    // Constructor - Initializes database connection
    public DeleteQuery() {
        // Properties object to hold database connection info
        Properties props = new Properties();
        // Input stream to read the dbConn.properties file
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            // Loading properties from the file
            props.load(instr);
        } catch (IOException ex) {
            // Logging an error if properties file cannot be loaded
            Logger.getLogger(DeleteQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Closing the input stream
            instr.close();
        } catch (IOException ex) {
            // Logging an error if input stream cannot be closed
            Logger.getLogger(DeleteQuery.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeleteQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Establishing a connection to the database
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            // Logging an error if connection fails
            Logger.getLogger(DeleteQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to delete a FoodItem from the database by its ID
    public void DoDelete(int FoodItemID) {
        try {
            // SQL query to delete a record from the FoodItem table
            String query = "DELETE FROM FoodItem WHERE FoodItemID = ?";

            // Preparing the SQL statement
            PreparedStatement ps = conn.prepareStatement(query);

            // Setting the FoodItemID parameter for the delete query
            ps.setInt(1, FoodItemID);

            // Executing the update to delete the record
            ps.executeUpdate();
        } catch (SQLException ex) {
            // Logging an error if the delete operation fails
            Logger.getLogger(DeleteQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
