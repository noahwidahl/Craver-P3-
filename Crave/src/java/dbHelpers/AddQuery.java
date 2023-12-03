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

public class AddQuery {
    
    private Connection conn; // Variable for database connection

    // Constructor for initializing the AddQuery object
    public AddQuery() {
        Properties props = new Properties(); // Object to hold database properties
        try (InputStream instr = getClass().getResourceAsStream("dbConn.properties")) {
            props.load(instr); // Load properties from the file

            // Retrieving database connection parameters
            String driver = props.getProperty("driver.name");
            String url = props.getProperty("server.name");
            String username = props.getProperty("user.name");
            String passwd = props.getProperty("user.password");

            // Loading database driver class and establishing a connection
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            // Logging any exceptions that occur during database connection setup
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to add a FoodItem to the database
    public void doAdd(FoodItem foodItem) throws SQLException {
        // SQL query to insert a new record into the FoodItem table
        String query = "INSERT INTO FoodItem (FoodItemName, Price, FoodsupplierID) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            // Setting the parameters for the insert query
            ps.setString(1, foodItem.getFoodItemName());
            ps.setBigDecimal(2, foodItem.getPrice());
            ps.setInt(3, foodItem.getFoodSupplierID());

            // Executing the update to insert the record
            ps.executeUpdate();
        } finally {
            // Closing the connection in the finally block to ensure it's closed even if an exception occurs
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                // Logging an error if closing the connection fails
                Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
