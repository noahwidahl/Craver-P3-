package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.util.logging.Logger;



/**
 *
 * @author sorennygaardjensen
 */
import java.sql.SQLException;
import model.FoodItem;
public class ReadQuery {
    
    private Connection conn;
    private ResultSet results;
    
    public ReadQuery(){
        
        Properties props = new Properties();
        InputStream instr = getClass() .getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
            
    public void doRead(){
        
        try {
            String query ="SELECT * from FoodItem";
            
            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String getHTMLTable(){
        
        String table = "";
        table += "<table border=1>";
        
        try {
            while(this.results.next()){
                
                FoodItem FoodItem = new FoodItem();
                FoodItem.setFoodItemID(this.results.getInt("FoodItemID")); 
                FoodItem.setFoodItemName(this.results.getString("FoodItemName"));
                FoodItem.setPrice(this.results.getBigDecimal("price"));
                
                table += "<tr>";
                table +="<td>";
                table += FoodItem.getFoodItemID();
                table +="</td>";  
                
                table +="<td>";
                table += FoodItem.getFoodItemName();
                table +="</td>";
                
                table +="<td>";
                table += FoodItem.getPrice();
                table +="</td>";
                
                table +="<td>";
                table += "<a href=update?FoodItemID=" + FoodItem.getFoodItemID() + "> Update </a>" + "<a href=delete?FoodItemID=" + FoodItem.getFoodItemID() + "> Delete </a>";
                table +="</td>";
                
                table += "<tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        table += "</table>";
        
                return table;
    }

  
}