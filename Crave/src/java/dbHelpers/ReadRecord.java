package dbHelpers;

// Importing necessary Java SQL and IO classes, and the FoodItem model
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

public class ReadRecord {

    // Variables for managing database connection and query results
    private Connection conn;
    private ResultSet results;
    private FoodItem foodItem; // Variable to hold the FoodItem object
    private int foodItemID; // ID of the FoodItem to be retrieved

    // Constructor to initialize a ReadRecord object with a specific FoodItem ID
    public ReadRecord(int foodItemID) {
        Properties props = new Properties(); // Object to hold database properties
        InputStream instr = getClass().getResourceAsStream("dbConn.properties"); // Reading database configuration file
        try {
            props.load(instr); // Load properties from the file
            instr.close(); // Close the input stream

            // Extracting database connection details from properties
            String driver = props.getProperty("driver.name");
            String url = props.getProperty("server.name");
            String username = props.getProperty("user.name");
            String passwd = props.getProperty("user.password");

            this.foodItemID = foodItemID; // Assigning the FoodItem ID

            // Loading database driver and establishing connection
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (Exception ex) {
            // Logging any exceptions that occur during database connection setup
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to perform the database read operation
    public void doRead() {
        try {
            // SQL query to fetch a specific FoodItem by its ID
            String query = "SELECT * FROM FoodItem WHERE FoodItemID = ?";
            PreparedStatement ps = conn.prepareStatement(query); // Preparing the SQL statement
            ps.setInt(1, foodItemID); // Setting the FoodItem ID in the SQL query
            this.results = ps.executeQuery(); // Executing the query

            this.results.next(); // Moving to the first record in the result set

            // Creating a FoodItem object from the result set
            foodItem = new FoodItem(
                this.results.getInt("FoodItemID"),
                this.results.getString("FoodItemName"),
                this.results.getBigDecimal("Price"),
                this.results.getString("LinkToFoodImage"),
                this.results.getInt("FoodsupplierID"),
                this.results.getTimestamp("AddDate"),
                this.results.getTimestamp("ModifiedDate")
            );
        } catch (SQLException ex) {
            // Logging any SQL exceptions that occur during the read operation
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Getter method for the FoodItem object
    public FoodItem getFooditem() {
        return this.foodItem; // Returns the FoodItem object retrieved from the database
    }

}
