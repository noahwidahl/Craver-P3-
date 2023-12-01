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

    public class ReadRecord {

    private Connection conn;
    private ResultSet results;
    private FoodItem foodItem; // Declare the FoodItem object without instantiating
    private int foodItemID;

    public ReadRecord(int foodItemID) {
        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
            instr.close();

            String driver = props.getProperty("driver.name");
            String url = props.getProperty("server.name");
            String username = props.getProperty("user.name");
            String passwd = props.getProperty("user.password");

            this.foodItemID = foodItemID;

            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (Exception ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doRead() {
        try {
            String query = "SELECT * FROM FoodItem WHERE FoodItemID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, foodItemID);
            this.results = ps.executeQuery();

            this.results.next();

            // Instantiate the FoodItem object here after results are fetched
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
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FoodItem getFooditem() {
        return this.foodItem; // Return the instantiated FoodItem object
    }

    // Remember to include the necessary close methods to close the ResultSet and Connection when done
}