package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FoodItem;

public class ReadQuery {

    private Connection conn;
    private ResultSet results;
    private Integer supplierId; // Using Integer to allow null

    // Constructor for all food items
    public ReadQuery() {
        this(null); // Call the main constructor with null
    }

    // Main constructor that can filter by supplierId
    public ReadQuery(Integer supplierId) {
        this.supplierId = supplierId;

        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doRead() {
        try {
            String query;
            if (this.supplierId != null) {
                query = "SELECT * FROM FoodItem WHERE FoodsupplierID = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, this.supplierId);
                this.results = ps.executeQuery();
            } else {
                query = "SELECT * FROM FoodItem";
                PreparedStatement ps = conn.prepareStatement(query);
                this.results = ps.executeQuery();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getHTMLTable() {
        StringBuilder table = new StringBuilder();
        table.append("<table border='1'>");

        try {
            while (this.results != null && this.results.next()) {
                FoodItem foodItem = new FoodItem(
                    this.results.getInt("FoodItemID"),
                    this.results.getString("FoodItemName"),
                    this.results.getBigDecimal("Price"),
                    this.results.getString("LinkToFoodImage"),
                    this.results.getInt("FoodsupplierID"),
                    this.results.getTimestamp("AddDate"),
                    this.results.getTimestamp("ModifiedDate")
                );

                table.append("<tr>");
                table.append("<td>").append(foodItem.getFoodItemID()).append("</td>");
                table.append("<td>").append(foodItem.getFoodItemName()).append("</td>");
                table.append("<td>").append(foodItem.getPrice()).append("</td>");
                table.append("<td>").append(foodItem.getLinkToFoodImage()).append("</td>");
                table.append("<td>").append(foodItem.getFoodSupplierID()).append("</td>");
                table.append("<td>").append(foodItem.getAddDate()).append("</td>");
                table.append("<td>").append(foodItem.getModifiedDate()).append("</td>");
                table.append("<td>");
                table.append("<a href='update?FoodItemID=").append(foodItem.getFoodItemID()).append("'>Update</a>");
                table.append(" | ");
                table.append("<a href='delete?FoodItemID=").append(foodItem.getFoodItemID()).append("&FoodSupplierID=").append(foodItem.getFoodSupplierID()).append("'>Delete</a>");
                table.append("</td>");
                table.append("</tr>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.append("</table>");
        return table.toString();
    }
}
