/*
 * Class to handle database operations related to adding FoodItem records.
 */
package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import model.FoodItem;

public class AddQuery {
    
    private Connection conn; // Database connection object

    // Constructor - Initializes database connection
    public AddQuery() {
        // Properties object to hold database connection info
        Properties props = new Properties();
        // Input stream to read the dbConn.properties file
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            // Loading properties from the file
            props.load(instr);
        } catch (IOException ex) {
            // Logging an error if properties file cannot be loaded
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Closing the input stream
            instr.close();
        } catch (IOException ex) {
            // Logging an error if input stream cannot be closed
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Establishing a connection to the database
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            // Logging an error if connection fails
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to add a FoodItem to the database
    public void doAdd(FoodItem FoodItem) {
        try {
            // SQL query to insert a new record into FoodItem table
            String query = "INSERT INTO FoodItem (FoodItemName, Price) VALUES(?,?)";

            // Preparing the SQL statement
            PreparedStatement ps = conn.prepareStatement(query);

            // Setting parameters for the insert query
            ps.setString(1, FoodItem.getFoodItemName());
            ps.setBigDecimal(2, FoodItem.getPrice());

            // Executing the update
            ps.executeUpdate();
        } catch (SQLException ex) {
            // Logging an error if the insert operation fails
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
