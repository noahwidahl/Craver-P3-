package dbHelpers;

// Importing necessary Java SQL, IO classes, and the FoodItem model
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FoodItem;

public class ReadQuery {

    // Variables for managing database connection and query results
    private Connection conn;
    private ResultSet results;
    private Integer supplierId; // Using Integer to allow for null values, useful for optional filtering

    // Constructor for querying all food items (no specific supplier)
    public ReadQuery() {
        this(null); // Delegates to the main constructor with null, indicating no supplier filter
    }

    // Main constructor that can optionally filter by supplierId
    public ReadQuery(Integer supplierId) {
        this.supplierId = supplierId; // Set the supplier ID for filtering

        Properties props = new Properties(); // Object to hold database properties
        InputStream instr = getClass().getResourceAsStream("dbConn.properties"); // Reading database configuration file
        try {
            props.load(instr); // Load properties from the file
            instr.close(); // Close the input stream
        } catch (IOException ex) {
            // Logging any IO exceptions that occur while reading the properties file
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Extracting database connection details from properties
        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");

        try {
            // Loading database driver and establishing connection
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (ClassNotFoundException | SQLException ex) {
            // Logging any exceptions that occur during database connection setup
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to perform the database read operation
    public void doRead() {
        try {
            String query;
            // Check if a supplierId filter is set
            if (this.supplierId != null) {
                // Query to fetch FoodItems for a specific supplier
                query = "SELECT * FROM FoodItem WHERE FoodsupplierID = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, this.supplierId); // Set the supplier ID in the query
                this.results = ps.executeQuery();
            } else {
                // Query to fetch all FoodItems if no supplier filter is set
                query = "SELECT * FROM FoodItem";
                PreparedStatement ps = conn.prepareStatement(query);
                this.results = ps.executeQuery();
            }
        } catch (SQLException ex) {
            // Logging any SQL exceptions that occur during the read operation
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to generate an HTML table representation of the query results
    public String getHTMLTable() {
        StringBuilder table = new StringBuilder();
        table.append("<table border='1'>"); // Start of HTML table

        try {
            // Iterate through the ResultSet and populate the HTML table
            while (this.results != null && this.results.next()) {
                // Creating a FoodItem object from the current row in the ResultSet
                FoodItem foodItem = new FoodItem(
                    this.results.getInt("FoodItemID"),
                    this.results.getString("FoodItemName"),
                    this.results.getBigDecimal("Price"),
                    this.results.getString("LinkToFoodImage"),
                    this.results.getInt("FoodsupplierID"),
                    this.results.getTimestamp("AddDate"),
                    this.results.getTimestamp("ModifiedDate")
                );

                // Append a row to the HTML table for each FoodItem
                table.append("<tr>");
                // Append each field of FoodItem as a table cell
                table.append("<td>").append(foodItem.getFoodItemID()).append("</td>");
                table.append("<td>").append(foodItem.getFoodItemName()).append("</td>");
                table.append("<td>").append(foodItem.getPrice()).append("</td>");
                table.append("<td>").append(foodItem.getLinkToFoodImage()).append("</td>");
                table.append("<td>").append(foodItem.getFoodSupplierID()).append("</td>");
                table.append("<td>").append(foodItem.getAddDate()).append("</td>");
                table.append("<td>").append(foodItem.getModifiedDate()).append("</td>");
                // Append update and delete links for each FoodItem
                table.append("<td>");
                table.append("<a href='update?FoodItemID=").append(foodItem.getFoodItemID()).append("'>Update</a>");
                table.append(" | ");
                table.append("<a href='delete?FoodItemID=").append(foodItem.getFoodItemID()).append("&FoodSupplierID=").append(foodItem.getFoodSupplierID()).append("'>Delete</a>");
                table.append("</td>");
                table.append("</tr>");
            }
        } catch (SQLException ex) {
            // Logging any SQL exceptions that occur while processing the ResultSet
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.append("</table>"); // End of HTML table
        return table.toString(); // Return the HTML table as a string
    }
}
