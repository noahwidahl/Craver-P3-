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
    
    private Connection conn;

    public AddQuery() {
        Properties props = new Properties();
        try (InputStream instr = getClass().getResourceAsStream("dbConn.properties")) {
            props.load(instr);

            String driver = props.getProperty("driver.name");
            String url = props.getProperty("server.name");
            String username = props.getProperty("user.name");
            String passwd = props.getProperty("user.password");

            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doAdd(FoodItem foodItem) throws SQLException {
        String query = "INSERT INTO FoodItem (FoodItemName, Price, FoodsupplierID) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, foodItem.getFoodItemName());
            ps.setBigDecimal(2, foodItem.getPrice());
            ps.setInt(3, foodItem.getFoodSupplierID());

            ps.executeUpdate();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
