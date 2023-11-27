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

public class UpdateQuery {
    
    private Connection conn; 
    
    public UpdateQuery() {
        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
            instr.close();

            String driver = props.getProperty("driver.name");
            String url = props.getProperty("server.name");     
            String username = props.getProperty("user.name");
            String passwd = props.getProperty("user.password");    

            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void doUpdate(FoodItem FoodItem) {
        try {
            // Corrected SQL query
            String query = "UPDATE FoodItem SET FoodItemName = ?, Price = ? WHERE FoodItemID = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, FoodItem.getFoodItemName());
            ps.setBigDecimal(2, FoodItem.getPrice());
            ps.setInt(3, FoodItem.getFoodItemID()); // Set the FoodItemID for the WHERE clause

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
